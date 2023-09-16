// Copyright 2023 dinosdev.cn.
// SPDX-License-Identifier: Apache-2.0

package org.dinospring.modules.im;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Cody Lu
 * @date 2023-08-11 17:52:05
 */

public interface ImService {

  /**
   * 向IM系统注册用户
   * @param userInfo 用户信息
   * @return true：注册成功，false：注册失败
   */
  boolean registerUser(ImUserInfo userInfo) throws IOException;

  /**
   * 检查用户是否已在IM系统注册
   * @param userId
   * @return 是否已注册
   */
  boolean isRegisteredUser(String userId) throws IOException;

  /**
   * 更新IM系统里的用户信息
   * @param userInfo
   * @return true：更新成功，false：更新失败
   */
  boolean updateUserInfo(ImUserInfo userInfo) throws IOException;

  /**
   * 从IM系统删除用户
   * @param userId
   * @return true：删除成功，false：删除失败
   */
  boolean deleteUser(String userId) throws IOException;

  // 开始写批量增加、更新、删除用户的接口

  /**
   * 批量注册用户
   * @param userInfoList
   * @return true：注册成功，false：注册失败
   * @throws IOException
   */
  public boolean batchRegisterUser(Collection<ImUserInfo> userInfoList) throws IOException;

  /**
   * 批量更新用户信息
   * @param userInfoList
   * @return true：更新成功，false：更新失败
   */
  public boolean batchUpdateUserInfo(Collection<ImUserInfo> userInfoList) throws IOException;

  /**
   * 批量删除用户
   * @param userIdList
   * @return true：删除成功，false：删除失败
   */
  public boolean batchDeleteUser(Collection<String> userIdList) throws IOException;

  /**
   * 获取用户访问IM系统的Token
   * @param userId
   * @param platform
   * @return 用户Token，如果失败，则返回null
   * @throws IOException
   */
  ImAuthToken getUserAuthToken(String userId, ImPlatformEnum platform) throws IOException;

  /**
   * 创建群组
   * @param groupInfo
   * @param adminUserIds
   * @param memberUserIds
   * @return
   * @throws IOException
   */
  boolean createGroup(ImGroupInfo groupInfo, Collection<String> adminUserIds, Collection<String> memberUserIds)
      throws IOException;

  /**
   * 解散群组
   * @param groupId
   * @return
   * @throws IOException
   */
  boolean dismissGroup(String groupId) throws IOException;

  /**
   * 更新群组信息
   * @param groupInfo
   * @return
   * @throws IOException
   */
  boolean updateGroupInfo(ImGroupInfo groupInfo) throws IOException;

  /**
   * 获取群组信息
   * @param groupId
   * @return
   * @throws IOException
   */
  ImGroupInfo getGroupInfo(String groupId) throws IOException;

  /**
   * 获取群组成员列表
   * @param groupId
   * @return
   * @throws IOException
   */
  Page<ImGroupMemberInfo> getGroupMemberList(String groupId, Pageable page) throws IOException;

  /**
   * 获取群组成员信息
   * @param groupId
   * @param userIds
   * @return
   * @throws IOException
   */
  List<ImGroupMemberInfo> getGroupMemberInfo(String groupId, Collection<String> userIds) throws IOException;

  /**
   * 添加群组成员
   * @param groupId
   * @param userIds
   * @return
   * @throws IOException
   */
  boolean addGroupMembers(String groupId, Collection<String> userIds) throws IOException;

  /**
   * 移除群组成员
   * @param groupId
   * @param userIds
   * @return
   * @throws IOException
   */
  boolean removeGroupMembers(String groupId, Collection<String> userIds) throws IOException;

  /**
   * 设置群组成员的角色
   * @param groupId
   * @param userIds
   * @param role
   * @return
   * @throws IOException
   */
  boolean setGroupMemberRole(String groupId, Collection<String> userIds, ImMemberRoleEnum role) throws IOException;

  /**
   * 将群组成员禁言
   * @param groupId
   * @param userIds
   * @param mutedSeconds
   * @return
   * @throws IOException
   */
  boolean muteGroupMember(String groupId, Collection<String> userIds, long mutedSeconds) throws IOException;

  /**
   * 取消群组成员的禁言状态
   * @param groupId
   * @param userIds
   * @return
   * @throws IOException
   */
  boolean unmuteGroupMember(String groupId, Collection<String> userIds) throws IOException;

  /**
   * 将群组禁言
   * @param groupId
   * @return
   * @throws IOException
   */
  boolean muteGroup(String groupId) throws IOException;

  /**
   * 取消群组的禁言状态
   * @param groupId
   * @return
   * @throws IOException
   */
  boolean unmuteGroup(String groupId) throws IOException;

  // 发送消息相关接口

  /**
   * 发送消息
   * @param msg
   * @param fromUserId
   * @param toUserIds
   * @return
   * @throws IOException
   */
  boolean sendMessage(ImMessage msg, String fromUserId, Collection<String> toUserIds) throws IOException;

  /**
   * 发送群消息
   * @param msg
   * @return
   * @throws IOException
   */

  boolean sendGroupMessage(ImGroupMessage msg) throws IOException;

  // 发送agent消息相关接口

  /**
   * 发送agent消息
   * @param msg
   * @param agentId
   * @param toUserIds
   * @return
   * @throws IOException
   */
  boolean sendAgentMessage(ImMessage msg, String agentId, Collection<String> toUserIds) throws IOException;

  /**
   * 撤回消息
   * @param conversationId
   * @param msgId
   * @return
   * @throws IOException
   */

  boolean recallMessage(String conversationId, String msgId) throws IOException;

}