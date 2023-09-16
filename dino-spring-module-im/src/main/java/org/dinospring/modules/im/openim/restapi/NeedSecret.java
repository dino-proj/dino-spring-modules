// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.openim.restapi;

/**
 *
 * @author Cody Lu
 * @date 2023-08-12 00:22:52
 */

public interface NeedSecret {
  /**
   * OpenIM秘钥
   * @param secret
   */
  void setSecret(String secret);
}