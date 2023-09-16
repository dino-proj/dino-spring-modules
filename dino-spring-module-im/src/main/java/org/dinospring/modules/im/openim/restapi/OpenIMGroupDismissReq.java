// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Cody Lu
 * @date 2023-09-15 23:32:48
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OpenIMGroupDismissReq extends OpenIMRequest {
  public static final String PATH = "/group/dismiss_group";

  /**
   * 群ID
   */
  @JsonProperty("groupID")
  private String groupId;

  @Override
  public String getApiPath() {
    return PATH;
  }
}