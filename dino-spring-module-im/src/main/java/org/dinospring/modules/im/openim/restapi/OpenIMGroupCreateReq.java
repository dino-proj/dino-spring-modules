// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Cody Lu
 * @date 2023-09-16 14:38:11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OpenIMGroupCreateReq extends OpenIMRequest {
  public static final String PATH = "/group/create_group";

  /**
   * 群主UserID
   */
  @JsonProperty("ownerUserID")
  private String ownerUserId;

  /**
   * 指定初始群成员
   */
  @JsonProperty("memberUserIDs")
  private List<String> memberUserIds;

  /**
   * 指定初始群管理员
   */
  @JsonProperty("adminUserIDs")
  private List<String> adminUserIds;

  /**
   * 群信息
   */
  @JsonProperty("groupInfo")
  private OpenIMGroupInfo groupInfo;

  @Override
  public String getApiPath() {
    return PATH;
  }
}
