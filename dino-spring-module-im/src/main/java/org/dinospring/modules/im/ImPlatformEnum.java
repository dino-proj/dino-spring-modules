// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im;

import lombok.Getter;

/**
 *
 * @author Cody Lu
 * @date 2023-08-12 07:19:47
 */

public enum ImPlatformEnum {
  IOS("IOS"),
  ANDROID("Android"),
  WINDOWS("Windows"),
  OSX("OSX"),
  WEB("Web"),
  MINI_APP("MiniAPP"),
  LINUX("Linux"),
  ANDROID_PAD("APad"),
  IPAD("IPad"),
  ADMIN("Admin");

  @Getter
  private String platformCode;

  private ImPlatformEnum(String platformCode) {
    this.platformCode = platformCode;
  }

}