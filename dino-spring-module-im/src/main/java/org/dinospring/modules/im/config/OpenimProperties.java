// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * openim的配置
 * @author Cody Lu
 * @date 2022-04-13 03:11:08
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = OpenimProperties.PREFIX, name = "uri")
@ConfigurationProperties(prefix = OpenimProperties.PREFIX)
public class OpenimProperties {
  public static final String PREFIX = "dinospring.module.im.openim";

  /**
   * openim服务器地址，格式为：http://host:port
   */
  private String uri;

  /**
   * openim管理员userID，此处的userID必须为配置文件config/config.yaml的appManagerUid的其中一个
   */
  private String adminId;

  /**
   * openim秘钥
   */
  private String secret;

}