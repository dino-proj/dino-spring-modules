// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 *
 * @author Cody Lu
 * @date 2023-09-15 23:17:07
 */

@Data
public class OpenIMGroupInfo {

  /**
   * 群类型
   */
  @JsonProperty("groupType")
  private int groupType;

  /**
   * 群ID
   */
  @JsonProperty("groupID")
  private String groupId;

  /**
   * 群名称
   */
  @JsonProperty("groupName")
  private String groupName;

  /**
   * 群公告
   */
  @JsonProperty("notification")
  private String notification;

  /**
   * 群介绍
   */
  @JsonProperty("introduction")
  private String introduction;

  /**
   * 群头像URL
   */
  @JsonProperty("faceURL")
  private String faceUrl;

  /**
   * 是否需要验证
   */
  @JsonProperty("needVerification")
  private int needVerification;

  /**
   * 是否允许群成员查看群组成员信息
   */
  @JsonProperty("lookMemberInfo")
  private int lookMemberInfo;

  /**
   * 是否允许群成员邀请好友加群
   */
  @JsonProperty("applyMemberFriend")
  private int applyMemberFriend;

  /**
   * 群扩展信息
   */
  @JsonProperty("ex")
  private String ex;

}