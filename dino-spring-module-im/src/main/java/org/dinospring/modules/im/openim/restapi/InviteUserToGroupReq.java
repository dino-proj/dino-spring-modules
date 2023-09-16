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
 * @date 2023-09-16 14:39:01
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class InviteUserToGroupReq extends OpenIMRequest {

  public static final String PATH = "/group/invite_user_to_group";

  @JsonProperty(value = "groupID", required = true)
  private String groupId;

  @JsonProperty(value = "invitedUserIDList", required = true)
  private List<String> invitedUserIds;

  @JsonProperty("reason")
  private String reason;

  @Override
  public boolean isRequiredToken() {
    return true;
  }
}
