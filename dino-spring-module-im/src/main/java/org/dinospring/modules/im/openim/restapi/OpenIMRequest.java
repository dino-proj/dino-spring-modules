// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * openim restful api请求基类
 * @author Cody Lu
 * @date 2022-04-12 20:30:32
 */

public abstract class OpenIMRequest {

  /**
   * 请求路径
   * @return
   */
  @JsonIgnore
  public abstract String getApiPath();

  /**
   * 请求的时候是否需要token
   * @return
   */
  public boolean isRequiredToken() {
    return false;
  }

  /**
   * 请求的时候是否需要secret
   * @return
   */
  public boolean isRequiredSecret() {
    return false;
  }
}
