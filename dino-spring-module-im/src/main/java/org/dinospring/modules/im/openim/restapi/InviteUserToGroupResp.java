// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 *
 * @author Cody Lu
 * @date 2023-09-16 14:38:50
 */

@Data
public class InviteUserToGroupResp {

  @JsonProperty("userID")
  private String userId;

  @JsonProperty("result")
  private Integer result;

}
