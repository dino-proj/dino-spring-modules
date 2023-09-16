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
 * @date 2023-09-16 14:39:35
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountCheckReq extends OpenIMRequest {
  public static final String PATH = "/user/account_check";

  /**
   * 需要check的用户userID数组，单次数量不超过100
   */
  @JsonProperty("checkUserIDs")
  private List<String> checkUserIDs;

  @Override
  public boolean isRequiredToken() {
    return true;
  }

  @Override
  public String getApiPath() {
    return PATH;
  }

}
