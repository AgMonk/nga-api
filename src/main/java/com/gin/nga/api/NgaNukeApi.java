package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.params.nuke.DelAttachParam;
import com.gin.nga.params.nuke.TopicKeyParam;
import com.gin.nga.params.nuke.UserInfoParam;
import com.gin.nga.response.body.BaseMessageBody;
import com.gin.nga.response.body.nuke.TopicKeys;
import com.gin.nga.response.body.nuke.UserInfoBody;
import lombok.RequiredArgsConstructor;

/**
 * 综合操作相关API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:21
 */
@RequiredArgsConstructor
public class NgaNukeApi {
    private final NgaClient client;
    /**
     * 删除附件
     * @param topicId  主题id
     * @param replyId  回复id
     * @param filename 文件名
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/18 16:00
     */
    public NgaJsonCall<BaseMessageBody> delAttachment(DelAttachParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }
    /**
     * 查询版面的主题分类
     * @param forumId 版面id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.TopicKeys>
     * @since 2023/4/18 14:46
     */
    public NgaJsonCall<TopicKeys> getTopicKey(long forumId) {
        return client.nuke(new TopicKeyParam(forumId), TopicKeys.class);
    }
    /**
     * 查询用户信息
     * @param userId 用户id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.UserInfoBody>
     * @since 2023/4/17 11:28
     */
    public NgaJsonCall<UserInfoBody.Res> getUserInfo(long userId) {
        return client.nuke(new UserInfoParam(userId), UserInfoBody.Res.class);
    }

    /**
     * 查询用户信息
     * @param username 用户名
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.UserInfoBody>
     * @since 2023/4/17 11:28
     */
    public NgaJsonCall<UserInfoBody.Res> getUserInfo(String username) {
        return client.nuke(new UserInfoParam(username), UserInfoBody.Res.class);
    }


}
