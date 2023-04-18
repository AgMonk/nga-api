package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.enums.ReplyStatus;
import com.gin.nga.params.nuke.NoticeParam;
import com.gin.nga.params.nuke.RecommendParam;
import com.gin.nga.params.nuke.UserInfoParam;
import com.gin.nga.response.body.BaseMessageBody;
import com.gin.nga.response.body.nuke.NoticeBody;
import com.gin.nga.response.body.nuke.RecommendBody;
import com.gin.nga.response.body.nuke.UserInfoBody;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

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
     * 点赞
     * @param topicId 主题id
     * @param replyId 回复id 主楼传0
     * @return com.gin.nga.call.NgaJsonCall<?>
     * @since 2023/4/17 17:13
     */
    public NgaJsonCall<RecommendBody> agree(long topicId, long replyId) {
        final RecommendParam param = new RecommendParam();
        param.setTopicId(topicId);
        param.setReplyId(replyId);
        param.setValue(1);
        return client.nuke(param, RecommendBody.class);
    }

    /**
     * 点踩
     * @param topicId 主题id
     * @param replyId 回复id 主楼传0
     * @return com.gin.nga.call.NgaJsonCall<?>
     * @since 2023/4/17 17:13
     */
    public NgaJsonCall<RecommendBody> disagree(long topicId, long replyId) {
        final RecommendParam param = new RecommendParam();
        param.setTopicId(topicId);
        param.setReplyId(replyId);
        param.setValue(-1);
        return client.nuke(param, RecommendBody.class);
    }

    /**
     * 查询提醒消息
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.NoticeBody.Res>
     * @since 2023/4/18 9:26
     */
    public NgaJsonCall<NoticeBody.Res> getNotice() {
        return client.nuke(new NoticeParam(), NoticeBody.Res.class);
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

    /**
     * 关闭 / 开启某一楼提醒消息, 关闭后回复状态中将出现 {@link  ReplyStatus} 的 NO_HINT 属性
     * @param topicId 主题id
     * @param replyId 回复id
     * @param enable  true = 开启 false = 关闭
     * @return com.gin.nga.call.NgaJsonCall<BaseMessageBody>
     * @since 2023/4/18 10:59
     */
    public NgaJsonCall<BaseMessageBody> enableNotice(long topicId, long replyId, boolean enable) {
        final HashMap<String, Serializable> param = new HashMap<>(5);
        param.put("func", "noti_tag");
        param.put("no_hint", enable ? 0 : 1);
        param.put("tid", topicId);
        param.put("pid", replyId);
        param.put("raw", 3);
        return client.callJson(NgaPhpApi.nuke, param, null, BaseMessageBody.class);
    }

}
