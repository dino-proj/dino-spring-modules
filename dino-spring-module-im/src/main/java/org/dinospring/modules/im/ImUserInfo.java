// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Cody Lu
 * @date 2023-08-11 18:55:10
 */

@Data
public class ImUserInfo {

  @Schema(description = "用户在IM系统的ID")
  private String userId;

  @Schema(description = "用户昵称")
  private String nickname;

  @Schema(description = "用户头像")
  private String avatarUrl;
}