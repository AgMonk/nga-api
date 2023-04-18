package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.params.nuke.RecommendParam;
import com.gin.nga.response.body.nuke.RecommendBody;
import lombok.RequiredArgsConstructor;

/**
 * 赞踩相关API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 13:38
 */
@RequiredArgsConstructor
public class NgaRecommendApi {
    private final NgaClient client;
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
}
