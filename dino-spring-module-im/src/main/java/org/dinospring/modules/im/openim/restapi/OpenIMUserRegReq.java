// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户注册请求
 * @author Cody LU
 * @date 2022-04-13 03:23:10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OpenIMUserRegReq extends OpenIMRequest implements NeedSecret {
  public static final String PATH = "/user/user_register";

  /**
   * OpenIM秘钥
   */
  @JsonProperty("secret")
  private String secret;

  @JsonProperty("users")
  private List<OpenIMUserInfo> users;

  public void addUserInfo(OpenIMUserInfo userInfo) {
    if (this.users == null) {
      this.users = new ArrayList<>();
    }
    this.users.add(userInfo);
  }

  @Override
  public String getApiPath() {
    return PATH;
  }
}