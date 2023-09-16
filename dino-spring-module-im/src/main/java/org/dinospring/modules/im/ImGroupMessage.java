// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Cody Lu
 * @date 2023-09-15 22:54:11
 */

public class ImGroupMessage {

  @Schema(description = "消息发送者ID")
  private String senderId;

  @Schema(description = "群组ID")
  private String groupId;

  @Schema(description = "消息体")
  private ImMessage message;

}
