// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * openim的restful接口返回结果
 * @author Cody Lu
 * @date 2022-04-12 20:14:42
 */

@Data
public abstract class OpenIMResponse<T> {
  /**
   * 返回码
   */
  @JsonProperty("errCode")
  private Integer errCode;

  /**
   * 返回信息
   */
  @JsonProperty("errMsg")
  private String errMsg;

  /**
  * 返回详情
  */
  @JsonProperty("errDlt")
  private String errDlt;

  /**
   * 返回数据
   */
  @JsonProperty("data")
  private T data;

}
