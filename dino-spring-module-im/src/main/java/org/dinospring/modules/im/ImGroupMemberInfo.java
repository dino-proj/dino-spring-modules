// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0
package org.dinospring.modules.im;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Cody Lu
 * @date 2023-09-14 22:23:32
 */

@Data
public class ImGroupMemberInfo {

  @Schema(description = "群组ID")
  private String groupId;

  @Schema(description = "用户在IM系统的ID")
  private String userId;

  @Schema(description = "用户在群组中的角色等级")
  private int roleLevel;

  @Schema(description = "用户加入群组的时间")
  private long joinTime;

  @Schema(description = "用户在本群中的昵称")
  private String nickname;

  @Schema(description = "用户头像URL")
  private String avatarUrl;

  @Schema(description = "用户在群组中的管理角色")
  private ImMemberRoleEnum role;

  @Schema(description = "用户加入群组的来源")
  private int joinSource;

  @Schema(description = "操作加群的用户ID")
  private String operatorUserId;

  @Schema(description = "用户在群组中的扩展信息")
  private String ex;

  @Schema(description = "用户被禁言的结束时间")
  private long muteEndTime;

  @Schema(description = "邀请进群的人的ID")
  private String inviterUserId;

}