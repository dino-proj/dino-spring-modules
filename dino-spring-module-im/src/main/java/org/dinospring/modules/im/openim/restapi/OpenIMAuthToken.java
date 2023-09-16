// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0
package org.dinospring.modules.im.openim.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 *
 * @author Cody Lu
 * @date 2023-08-12 07:29:37
 */

@Data
public class OpenIMAuthToken {

  @JsonProperty("token")
  private String token;

  @JsonProperty("expireTimeSeconds")
  private Long expireTimeSeconds;
}