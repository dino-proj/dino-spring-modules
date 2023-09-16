// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Cody Lu
 * @date 2023-09-11 15:56:56
 */

@Data
public class ImGroupInfo {

  @Schema(description = "群组ID")
  private String groupId;

  @Schema(description = "群组类型")
  private int groupType;

  @Schema(description = "群组名称")
  private String groupName;

  @Schema(description = "群组所有者ID")
  private String ownerUserId;

  @Schema(description = "群组公告")
  private String notification;

  @Schema(description = "群组简介")
  private String introduction;

  @Schema(description = "群组头像URL")
  private String avatarUrl;

  @Schema(description = "群组扩展信息")
  private String ex;

  @Schema(description = "是否需要验证")
  private boolean needVerification;

  @Schema(description = "是否允许群成员查看群组成员信息")
  private boolean allowVisitMemberInfo;

  @Schema(description = "是否允许群成员邀请好友加群")
  private boolean allowInviteMember;

  @Schema(description = "群状态")
  private String status;

}
