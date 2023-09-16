// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户token信息
 * @author Cody Lu
 * @date 2022-04-13 04:15:34
 */

@Data
public class ImAuthToken {

  @Schema(description = "用户ID")
  private String userId;

  @Schema(description = "登录后的Token信息")
  private String token;

  @Schema(description = "token过期时间戳（秒）")
  private Long expiredTime;
}
