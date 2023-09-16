// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import lombok.Data;

/**
 *
 * @author Cody Lu
 * @date 2023-09-16 14:39:25
 */

@Data
public class Group {

  private String creatorUserID;
  private String groupID;
  private String groupName;
  private int memberCount;
  private String ownerUserID;
}
