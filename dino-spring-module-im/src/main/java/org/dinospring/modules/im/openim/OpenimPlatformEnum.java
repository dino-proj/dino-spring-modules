// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim;

import org.dinospring.modules.im.ImPlatformEnum;

import lombok.Getter;

/**
 *
 * @author Cody Lu
 * @date 2023-08-12 07:19:47
 */

public enum OpenimPlatformEnum {
  IOS("IOS", 1),
  ANDROID("Android", 2),
  WINDOWS("Windows", 3),
  OSX("OSX", 4),
  WEB("Web", 5),
  MINI_WEB("MiniWeb", 6),
  LINUX("Linux", 7),
  ANDROID_PAD("APad", 8),
  IPAD("IPad", 9),
  ADMIN("Admin", 10);

  @Getter
  private int platformId;

  @Getter
  private String platformCode;

  private OpenimPlatformEnum(String platformCode, int platformId) {
    this.platformId = platformId;
    this.platformCode = platformCode;
  }

  public static int toPlatformId(ImPlatformEnum platform) {
    return switch (platform) {
      case IOS -> IOS.getPlatformId();
      case ANDROID -> ANDROID.getPlatformId();
      case WINDOWS -> WINDOWS.getPlatformId();
      case OSX -> OSX.getPlatformId();
      case WEB -> WEB.getPlatformId();
      case MINI_APP -> MINI_WEB.getPlatformId();
      case LINUX -> LINUX.getPlatformId();
      case ANDROID_PAD -> ANDROID_PAD.getPlatformId();
      case IPAD -> IPAD.getPlatformId();
      case ADMIN -> ADMIN.getPlatformId();
      default -> throw new IllegalArgumentException("Unknown platform: " + platform);
    };
  }

}