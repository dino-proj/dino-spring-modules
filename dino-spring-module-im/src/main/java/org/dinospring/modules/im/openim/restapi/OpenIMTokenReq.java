// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import org.dinospring.modules.im.openim.OpenimPlatformEnum;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Cody Lu
 * @date 2023-08-12 07:00:40
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class OpenIMTokenReq extends OpenIMRequest implements NeedSecret {
  public static final String PATH = "/auth/user_token";

  /**
   * OpenIM秘钥
   */
  @JsonProperty("secret")
  private String secret;

  /**
   * 用户id
   */
  @JsonProperty("userID")
  private String userId;

  /**
   * 平台ID，see {@link OpenimPlatformEnum}
   */
  @JsonProperty("platformID")
  private Integer platformId;

  @Override
  public String getApiPath() {
    return PATH;
  }

}