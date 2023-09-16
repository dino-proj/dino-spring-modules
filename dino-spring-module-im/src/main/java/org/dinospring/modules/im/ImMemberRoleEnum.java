// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0
package org.dinospring.modules.im;

/**
 *
 * @author Cody Lu
 * @date 2023-09-14 22:41:59
 */

public enum ImMemberRoleEnum {

  /**
   * 群主
   */
  OWNER(1, "群主"),

  /**
   * 管理员
   */
  ADMIN(2, "管理员"),

  /**
   * 普通成员
   */
  MEMBER(3, "普通成员");

  private int code;
  private String desc;

  private ImMemberRoleEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  /**
   * 获取role代码
   * @return
   */
  public int getCode() {
    return this.code;
  }

  /**
   * 获取role描述
   * @return
   */
  public String getDesc() {
    return this.desc;
  }
}