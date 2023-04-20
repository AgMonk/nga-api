package com.gin.nga.params.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.PostAction;
import lombok.Getter;

/**
 * 发帖准备参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 09:42
 */
@Getter
public class PrepareParam {
    /**
     * 发帖动作
     */
    @JsonProperty("action")
    final PostAction action;
    /**
     * 发布评论或贴条时设置为1
     */
    @JsonProperty("comment")
    final Integer comment;
    /**
     * 版面id
     */
    @JsonProperty("fid")
    final Long forumId;
    /**
     * 主题id
     */
    @JsonProperty("tid")
    final Long topicId;
    /**
     * 回复id
     */
    @JsonProperty("pid")
    final Long replyId;
    /**
     * 合集id
     */
    @JsonProperty("stid")
    final Long colTid;

    /**
     * 基础构造函数
     * @param action    动作
     * @param forumId   版面ID
     * @param isComment 是否以贴条发布
     * @param topicId   主题ID
     * @param replyId   回复ID
     * @param colTid    合集id
     * @since 2023/4/20 9:53
     */
    private PrepareParam(PostAction action, Long forumId, boolean isComment, Long topicId, Long replyId, Long colTid) {
        this.action = action;
        this.comment = isComment ? 1 : null;
        this.forumId = forumId;
        this.topicId = topicId;
        this.replyId = replyId;
        this.colTid = colTid;
    }

    /**
     * 发布新主题
     * @param forumId 版面ID
     * @param colTid  合集id
     * @return com.gin.nga.params.post.PostPrepareParam
     * @since 2023/4/20 9:59
     */
    public static PrepareParam newTopicParam(long forumId, Long colTid) {
        return new PrepareParam(PostAction.NEW, forumId, false, null, null, colTid);
    }
    /**
     * 引用
     * @param topicId 主题ID
     * @param replyId 回复ID
     * @return com.gin.nga.params.post.PostPrepareParam
     * @since 2023/4/20 10:00
     */
    public static PrepareParam quoteParam(long topicId, long replyId) {
        return new PrepareParam(PostAction.QUOTE, null, false, topicId, replyId, null);
    }
    /**
     * 回复
     * @param isComment 是否以贴条发布
     * @param topicId   主题ID
     * @param replyId   回复ID
     * @return com.gin.nga.params.post.PostPrepareParam
     * @since 2023/4/20 10:00
     */
    public static PrepareParam replyParam(long topicId, long replyId, boolean isComment) {
        return new PrepareParam(PostAction.REPLY, null, isComment, topicId, replyId, null);
    }
    /**
     * 编辑
     * @param topicId 主题ID
     * @param replyId 回复ID
     * @return com.gin.nga.params.post.PostPrepareParam
     * @author bx002
     * @since 2023/4/20 10:08
     */

    public static PrepareParam modifyParam(long topicId, long replyId){
        return new PrepareParam(PostAction.MODIFY, null, false, topicId, replyId, null);
    }

}
