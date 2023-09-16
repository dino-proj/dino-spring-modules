// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Cody Lu
 * @date 2023-09-16 14:37:51
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class OpenIMGroupMemberMuteCancelReq extends OpenIMRequest {

  public static final String PATH = "/group/cancel_mute_group_member";

  @JsonProperty("groupID")
  private String groupId;

  @JsonProperty("userID")
  private String userId;

  @Override
  public String getApiPath() {
    return PATH;
  }

}
