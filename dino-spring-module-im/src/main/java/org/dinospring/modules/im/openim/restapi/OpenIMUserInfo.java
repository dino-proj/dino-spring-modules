// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0
package org.dinospring.modules.im.openim.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 *
 * @author Cody Lu
 * @date 2023-08-11 23:53:33
 */

@Data
public class OpenIMUserInfo {
  /**
  * 用户id
  */
  @JsonProperty("userID")
  private String userId;

  /**
   * 用户昵称
   */
  @JsonProperty("nickname")
  private String nickname;

  /**
  * 用户头像或者群头像url，根据上下文理解
  */
  @JsonProperty("faceURL")
  private String faceUrl;
}