// Copyright 2022 dinodev.cn
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.dinospring.modules.im.openim;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dinospring.modules.im.ImAuthToken;
import org.dinospring.modules.im.ImGroupInfo;
import org.dinospring.modules.im.ImGroupMemberInfo;
import org.dinospring.modules.im.ImGroupMessage;
import org.dinospring.modules.im.ImMemberRoleEnum;
import org.dinospring.modules.im.ImMessage;
import org.dinospring.modules.im.ImPlatformEnum;
import org.dinospring.modules.im.ImService;
import org.dinospring.modules.im.ImUserInfo;
import org.dinospring.modules.im.config.OpenimProperties;
import org.dinospring.modules.im.openim.restapi.AccountCheck;
import org.dinospring.modules.im.openim.restapi.AccountCheckReq;
import org.dinospring.modules.im.openim.restapi.InviteUserToGroupReq;
import org.dinospring.modules.im.openim.restapi.InviteUserToGroupResp;
import org.dinospring.modules.im.openim.restapi.MessageReq;
import org.dinospring.modules.im.openim.restapi.MessageResp;
import org.dinospring.modules.im.openim.restapi.OpenIMGroupCreateReq;
import org.dinospring.modules.im.openim.restapi.OpenIMGroupDismissReq;
import org.dinospring.modules.im.openim.restapi.OpenIMGroupInfo;
import org.dinospring.modules.im.openim.restapi.OpenIMGroupMemberMuteCancelReq;
import org.dinospring.modules.im.openim.restapi.OpenIMGroupMemberMuteReq;
import org.dinospring.modules.im.openim.restapi.OpenIMGroupMuteCancelReq;
import org.dinospring.modules.im.openim.restapi.OpenIMGroupMuteReq;
import org.dinospring.modules.im.openim.restapi.OpenIMTokenReq;
import org.dinospring.modules.im.openim.restapi.OpenIMUserInfo;
import org.dinospring.modules.im.openim.restapi.OpenIMUserRegReq;
import org.dinospring.modules.im.openim.restapi.OpenIMUserUpdateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * openim的restful接口实现
 * @author Cody LU
 * @date 2022-04-13 03:19:03
 */

@Service
@ConditionalOnBean(OpenimProperties.class)
@Slf4j
public class OpenimService implements ImService {

  @Autowired
  private OpenimOperator op;

  /**
   * 获取用户token
   *
   * @param userId 要获取token的用户id
   * @param platform 用户登录或注册的平台类型，管理员填8
   * @return 用户token, 如果获取失败，返回null
   * @throws IOException
   */
  @Override
  public ImAuthToken getUserAuthToken(String userId, ImPlatformEnum platform) throws IOException {
    var request = new OpenIMTokenReq();
    request.setUserId(userId);
    request.setPlatformId(OpenimPlatformEnum.toPlatformId(platform));
    return this.op.postForObject(request, ImAuthToken.class);
  }

  /**
   * 邀请用户进群
   * @param inviteUserToGroupReq
   * @return
   */
  public List<InviteUserToGroupResp> inviteUserToGroup(InviteUserToGroupReq inviteUserToGroupReq) {
    return postForList(InviteUserToGroupReq.PATH, inviteUserToGroupReq, InviteUserToGroupResp.class);
  }

  /**
   * 管理员通过后台接口发送单聊群聊消息，可以以管理员身份发消息，也可以以其他用户的身份发消息，通过sendID区分
   * @param messageReq
   * @return
   */
  public MessageResp sendMsg(MessageReq messageReq) {
    String sendId = messageReq.getSendId();
    if (StringUtils.isBlank(sendId)) {
      sendId = properties.getAdminId();
    }
    messageReq.setSendId(sendId);
    return post(messageReq, MessageResp.class);
  }

  /**
   * 查询用户是否在IM中已经注册接口
   * @param accountCheckReq
   * @return
   */
  public List<AccountCheck> accountCheck(AccountCheckReq accountCheckReq) throws IOException {
    return postForList(accountCheckReq, AccountCheck.class);
  }

  @Override
  public boolean registerUser(ImUserInfo userInfo) throws IOException {
    return this.batchRegisterUser(Collections.singletonList(userInfo));
  }

  @Override
  public boolean isRegisteredUser(String userId) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isRegisteredUser'");
  }

  @Override
  public boolean updateUserInfo(ImUserInfo userInfo) throws IOException {
    var request = new OpenIMUserUpdateReq();
    var userInfoReq = new OpenIMUserInfo();
    userInfoReq.setUserId(userInfo.getUserId());
    userInfoReq.setNickname(userInfo.getNickname());
    userInfoReq.setFaceUrl(userInfo.getAvatarUrl());

    request.setUserInfo(userInfoReq);
    return this.op.post(request);
  }

  @Override
  public boolean deleteUser(String userId) throws IOException {
    throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
  }

  @Override
  public boolean batchRegisterUser(Collection<ImUserInfo> userInfoList) throws IOException {

    var request = new OpenIMUserRegReq();
    for (var userInfo : userInfoList) {

      var userInfoReq = new OpenIMUserInfo();
      userInfoReq.setUserId(userInfo.getUserId());
      userInfoReq.setNickname(userInfo.getNickname());
      userInfoReq.setFaceUrl(userInfo.getAvatarUrl());
      request.addUserInfo(userInfoReq);
    }

    return this.op.post(request);
  }

  @Override
  public boolean batchUpdateUserInfo(Collection<ImUserInfo> userInfoList) throws IOException {
    for (ImUserInfo imUserInfo : userInfoList) {
      this.updateUserInfo(imUserInfo);
    }
    return true;
  }

  @Override
  public boolean batchDeleteUser(Collection<String> userIdList) throws IOException {
    throw new UnsupportedOperationException("Unimplemented method 'batchDeleteUser'");
  }

  @Override
  public boolean createGroup(ImGroupInfo groupInfo, Collection<String> adminUserIds, Collection<String> memberUserIds)
      throws IOException {
    var groupReq = new OpenIMGroupCreateReq();
    groupReq.setOwnerUserId(groupInfo.getOwnerUserId());
    groupReq.setMemberUserIds(List.copyOf(memberUserIds));
    groupReq.setAdminUserIds(List.copyOf(adminUserIds));

    var groupInfoReq = new OpenIMGroupInfo();
    groupInfoReq.setGroupId(groupInfo.getGroupId());
    groupInfoReq.setGroupName(groupInfo.getGroupName());
    groupInfoReq.setGroupType(groupInfo.getGroupType());
    groupInfoReq.setNotification(groupInfo.getNotification());
    groupInfoReq.setIntroduction(groupInfo.getIntroduction());
    groupInfoReq.setFaceUrl(groupInfo.getAvatarUrl());
    groupInfoReq.setNeedVerification(groupInfo.isNeedVerification() ? 1 : 0);
    groupInfoReq.setLookMemberInfo(groupInfo.isAllowVisitMemberInfo() ? 1 : 0);
    groupInfoReq.setApplyMemberFriend(groupInfo.isAllowInviteMember() ? 1 : 0);
    groupInfoReq.setEx(groupInfo.getEx());

    groupReq.setGroupInfo(groupInfoReq);

    return this.op.post(groupReq);
  }

  @Override
  public boolean dismissGroup(String groupId) throws IOException {
    var request = new OpenIMGroupDismissReq();
    request.setGroupId(groupId);

    return this.op.post(request);
  }

  @Override
  public boolean updateGroupInfo(ImGroupInfo groupInfo) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateGroupInfo'");
  }

  @Override
  public ImGroupInfo getGroupInfo(String groupId) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getGroupInfo'");
  }

  @Override
  public Page<ImGroupMemberInfo> getGroupMemberList(String groupId, Pageable page) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getGroupMemberList'");
  }

  @Override
  public List<ImGroupMemberInfo> getGroupMemberInfo(String groupId, Collection<String> userIds) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getGroupMemberInfo'");
  }

  @Override
  public boolean addGroupMembers(String groupId, Collection<String> userIds) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addGroupMembers'");
  }

  @Override
  public boolean removeGroupMembers(String groupId, Collection<String> userIds) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'removeGroupMembers'");
  }

  @Override
  public boolean setGroupMemberRole(String groupId, Collection<String> userIds, ImMemberRoleEnum role)
      throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setGroupMemberRole'");
  }

  @Override
  public boolean muteGroupMember(String groupId, Collection<String> userIds, long mutedSeconds) throws IOException {
    var request = new OpenIMGroupMemberMuteReq();
    request.setGroupId(groupId);
    request.setMutedSeconds(mutedSeconds);

    for (String userId : userIds) {
      request.setUserId(userId);
      this.op.post(request);
    }

    return true;
  }

  @Override
  public boolean unmuteGroupMember(String groupId, Collection<String> userIds) throws IOException {
    var request = new OpenIMGroupMemberMuteCancelReq();
    request.setGroupId(groupId);

    for (String userId : userIds) {
      request.setUserId(userId);
      this.op.post(request);
    }

    return true;
  }

  @Override
  public boolean muteGroup(String groupId) throws IOException {
    var request = new OpenIMGroupMuteReq();
    request.setGroupId(groupId);

    return this.op.post(request);
  }

  @Override
  public boolean unmuteGroup(String groupId) throws IOException {
    var request = new OpenIMGroupMuteCancelReq();
    request.setGroupId(groupId);

    return this.op.post(request);
  }

  @Override
  public boolean sendMessage(ImMessage msg, String fromUserId, Collection<String> toUserIds) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
  }

  @Override
  public boolean sendGroupMessage(ImGroupMessage msg) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sendGroupMessage'");
  }

  @Override
  public boolean sendAgentMessage(ImMessage msg, String agentId, Collection<String> toUserIds) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sendAgentMessage'");
  }

  @Override
  public boolean recallMessage(String conversationId, String msgId) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'recallMessage'");
  }
}
