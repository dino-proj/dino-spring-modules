// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 *
 * @author Cody Lu
 * @date 2023-09-16 14:39:14
 */

@Data
public class GroupMember {

  /**
   * 用户 ID，必须保证IM内唯一
   */
  @JsonProperty("userID")
  private String userId;

  /**
   * 群内成员类型(1普通成员，2群主，3管理员)
   */
  @JsonProperty("roleLevel")
  private int roleLevel = 1;

}
