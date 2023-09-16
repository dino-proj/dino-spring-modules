// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;

/**
 *
 * @author Cody Lu
 * @date 2023-09-16 14:39:45
 */

@Data
public class AccountCheck {

  @JsonProperty("userID")
  private String userId;

  @JsonProperty("accountStatus")
  private String accountStatus;

  public enum AccountStatus {
    /**
     * 已注册
     */
    REGISTERED("registered", "已注册"),
    /**
     * 未注册
     */
    UNREGISTERED("unregistered", "未注册");

    @Getter
    private String status;

    @Getter
    private String desc;

    AccountStatus(String status, String desc) {
      this.status = status;
      this.desc = desc;
    }
  }
}
