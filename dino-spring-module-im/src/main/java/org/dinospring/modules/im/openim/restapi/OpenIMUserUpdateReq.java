// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息更新请求
 * @author Cody LU
 * @date 2023-09-11 14:07:55
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OpenIMUserUpdateReq extends OpenIMRequest {
  public static final String PATH = "/user/update_user_info";

  @JsonProperty("userInfo")
  private OpenIMUserInfo userInfo;

  @Override
  public String getApiPath() {
    return PATH;
  }
}