// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 *
 * @author Cody Lu
 * @date 2023-09-16 14:38:27
 */

@Data
public class MessageResp {

  /**
   * 发送者ID
   */
  @JsonProperty("sendTime")
  private String sendTime;

  /**
   * 发送者ID
   */
  @JsonProperty("serverMsgID")
  private String serverMsgId;

  /**
   * 发送者ID
   */
  @JsonProperty("clientMsgID")
  private String clientMsgId;
}
