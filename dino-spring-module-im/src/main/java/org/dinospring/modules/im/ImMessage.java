// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Cody Lu
 * @date 2023-09-14 22:57:41
 */

public interface ImMessage {

  @Schema(description = "消息类型", example = "text")
  String getMessageType();

  @Schema(description = "消息内容", example = "Hello World!")
  String getMessageContent();

  @Schema(description = "消息AT用户列表", example = "['user1', 'user2']")
  List<String> getAtUserList();

  @Schema(description = "引用的消息ID", example = "1234567890")
  String getQuoteMessageId();

}
