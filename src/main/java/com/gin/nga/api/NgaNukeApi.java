package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.params.nuke.RecommendParam;
import com.gin.nga.params.nuke.UserInfoParam;
import com.gin.nga.response.body.nuke.RecommendBody;
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

    //todo

    /**
     * 查询用户信息
     * @param username 用户名
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.UserInfoBody>
     * @since 2023/4/17 11:28
     */
    public NgaJsonCall<UserInfoBody.Res> getUserInfo(String username) {
        return client.nuke(new UserInfoParam(username), UserInfoBody.Res.class);
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
     * 点赞
     * @param topicId 主题id
     * @param replyId 回复id 主楼传0
     * @return com.gin.nga.call.NgaJsonCall<?>
     * @author bx002
     * @since 2023/4/17 17:13
     */
    public NgaJsonCall<RecommendBody> agree(long topicId,long replyId){
        final RecommendParam param = new RecommendParam();
        param.setTopicId(topicId);
        param.setReplyId(replyId);
        param.setValue(1);
        return client.nuke(param, RecommendBody.class);
    }    /**
     * 点踩
     * @param topicId 主题id
     * @param replyId 回复id 主楼传0
     * @return com.gin.nga.call.NgaJsonCall<?>
     * @author bx002
     * @since 2023/4/17 17:13
     */
    public NgaJsonCall<RecommendBody> disagree(long topicId,long replyId){
        final RecommendParam param = new RecommendParam();
        param.setTopicId(topicId);
        param.setReplyId(replyId);
        param.setValue(-1);
        return client.nuke(param, RecommendBody.class);
    }

}
