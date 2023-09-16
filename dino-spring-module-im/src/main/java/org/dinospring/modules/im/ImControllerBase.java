// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im;

import java.io.IOException;

import org.dinospring.commons.context.ContextHelper;
import org.dinospring.commons.response.Response;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

/**
 * Im模块的基础控制器
 * @author Cody Lu
 * @date 2022-04-13 03:09:25
 */

public interface ImControllerBase {

  /**
   * 获取当前用户的im ID
   *
   * @return
   */
  String currentUserImId();

  /**
   * 获取当前用户的Token
   *
   * @param platform 平台
   * @return 用户Token
   * @throws IOException
   */
  @Operation(summary = "获取当前用户的im系统 Token")
  @Parameter(in = ParameterIn.QUERY, name = "platform", description = "登录平台", required = true)
  @GetMapping("/auth")
  default Response<ImAuthToken> auth(ImPlatformEnum platform) throws IOException {
    var service = ContextHelper.findBean(ImService.class);
    return Response.success(service.getUserAuthToken(this.currentUserImId(), platform));
  }
}
