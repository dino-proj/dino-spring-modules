// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import org.dinospring.commons.utils.AsmUtils;
import org.dinospring.modules.im.config.OpenimProperties;
import org.dinospring.modules.im.openim.restapi.NeedSecret;
import org.dinospring.modules.im.openim.restapi.OpenIMAuthToken;
import org.dinospring.modules.im.openim.restapi.OpenIMRequest;
import org.dinospring.modules.im.openim.restapi.OpenIMResponse;
import org.dinospring.modules.im.openim.restapi.OpenIMTokenReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Cody Lu
 * @date 2023-08-12 00:33:25
 */

@Component
@ConditionalOnBean(OpenimProperties.class)
@Slf4j
public class OpenimOperator {
  @Autowired
  private RestTemplate restClient;

  @Autowired
  private OpenimProperties properties;

  private static OpenIMAuthToken adminToken = null;

  private static final AtomicLong ID_GEN = new AtomicLong(System.currentTimeMillis());

  /**
   * 发送请求
   * @param request 请求
   * @return 是否成功，true为成功code==0，else false
   * @throws IOException
   */
  public boolean post(OpenIMRequest request) throws IOException {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("operationID", String.valueOf(ID_GEN.incrementAndGet()));
    if (request.isRequiredToken()) {
      httpHeaders.set("token", this.getAdminToken());
    }

    if (request instanceof NeedSecret) {
      ((NeedSecret) request).setSecret(this.properties.getSecret());
    }

    HttpEntity<OpenIMRequest> httpEntity = new HttpEntity<>(request, httpHeaders);

    var resp = this.restClient.postForObject(this.makeUrl(request.getApiPath()), httpEntity, OpenIMResponse.class);
    if (Objects.nonNull(resp)) {
      if (resp.getErrCode() != 0) {
        log.error("ErrCode={}, ErrMsg={}\n request: {}", resp.getErrCode(), resp.getErrMsg(), request);
        return false;
      } else {
        return true;
      }
    } else {
      throw new IOException("no Response");
    }
  }

  /**
   * 发送请求，并返回IM系统的响应
   * @param <T> 响应结果类型
   * @param request 请求
   * @param respClass 响应结果类型
   * @return
   * @throws IOException
   */
  public <T> T postForObject(OpenIMRequest request, Class<T> respClass) throws IOException {

    var newClassName = AsmUtils.className(OpenIMResponse.class, respClass);
    Class<OpenIMResponse<T>> cls;
    try {
      cls = AsmUtils.defineGenericClass(newClassName, OpenIMResponse.class, respClass);
    } catch (Exception e) {
      log.error("defineGenericClass error", e);
      throw new IOException(e);
    }

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("operationID", String.valueOf(ID_GEN.incrementAndGet()));
    if (request.isRequiredToken()) {
      httpHeaders.set("token", this.getAdminToken());
    }

    if (request instanceof NeedSecret) {
      ((NeedSecret) request).setSecret(this.properties.getSecret());
    }

    HttpEntity<OpenIMRequest> httpEntity = new HttpEntity<>(request, httpHeaders);

    var resp = this.restClient.postForObject(this.makeUrl(request.getApiPath()), httpEntity, cls);
    if (Objects.nonNull(resp)) {
      if (resp.getErrCode() == 0) {
        return resp.getData();
      } else {
        throw new IOException(String.format("ErrCode=%s, ErrMsg=%s", resp.getErrCode(), resp.getErrMsg()));
      }
    } else {
      throw new IOException("no Response");
    }
  }

  /**
   * 向IM系统发送请求，并返回IM系统的响应
   * @param <T> 响应结果类型
   * @param request 请求
   * @param respClass 响应结果类型
   * @return
   * @throws IOException
   */
  public <T> List<T> postForList(OpenIMRequest request, Class<T> respClass) throws IOException {
    var newClassName = AsmUtils.className(ArrayList.class, respClass);
    Class<ArrayList<T>> newCls;
    try {
      newCls = AsmUtils.defineGenericClass(newClassName, ArrayList.class, respClass);
    } catch (Exception e) {
      log.error("defineGenericClass error", e);
      throw new IOException(e);
    }

    return this.postForObject(request, newCls);
  }

  /**
   * 获取用户的token
   * @param userId 用户ID
   * @param platform 平台ID
   * @return
   * @throws IOException
   */
  public OpenIMAuthToken getToken(String userId, int platform) throws IOException {
    var request = new OpenIMTokenReq();
    request.setUserId(userId);
    request.setPlatformId(platform);
    return this.postForObject(request, OpenIMAuthToken.class);
  }

  /**
   * 获取管理员的token
   * @return
   * @throws IOException
   */
  protected synchronized String getAdminToken() throws IOException {
    //判断token是否过期
    boolean needCreateToken = adminToken == null;
    if (adminToken != null) {
      Long expiredTime = adminToken.getExpireTimeSeconds();
      long currentTime = System.currentTimeMillis() / 1000 + 5 * 60; // 提前5分钟过期
      if (expiredTime < currentTime) {
        needCreateToken = true;
      }
    }
    if (needCreateToken) {
      String adminId = this.properties.getAdminId();
      adminToken = this.getToken(adminId, OpenimPlatformEnum.ADMIN.getPlatformId());
    }
    return adminToken.getToken();
  }

  /**
   * 构造请求的URL
   * @param path 请求的路径
   * @return
   */
  protected String makeUrl(String path) {
    return this.properties.getUri() + path;
  }
}