package com.gin.nga.response.post;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.BooleanDeserializer;
import com.gin.nga.enums.PostAction;
import com.gin.nga.enums.ReplyStatus;
import com.gin.nga.response.field.Attachment;
import com.gin.nga.response.field.CurrentUser;
import com.gin.nga.response.field.Forum;
import com.gin.nga.utils.StrUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 回复准备的响应
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 10:20
 */
@Getter
@Setter
public class PrepareBody {
    /**
     * 发帖动作
     */
    @JsonAlias("action")
    PostAction action;
    /**
     * todo 地址?
     */
    @JsonAlias("address")
    String address;
    /**
     * 上传地址
     */
    @JsonAlias("attach_url")
    String attachUrl;
    /**
     * 附件信息
     */
    @JsonAlias("attachs")
    LinkedHashMap<Integer, Attachment> attachments;
    /**
     * 校验码,准备请求中拿到的同名字段
     */
    @JsonAlias("auth")
    String auth;
    /**
     * 回复正文,编辑、回复、引用时这里会有数据
     */
    @JsonAlias("content")
    String content;
    /**
     * 超出编辑时限后正文将被放到这个字段里
     */
    @JsonAlias("content_org")
    String contentOriginal;
    /**
     * 版面id
     */
    @JsonAlias("fid")
    Long forumId;
    /**
     * 是否为隐藏回复
     */
    @JsonAlias("if_hidden")
    @JsonDeserialize(using = BooleanDeserializer.class)
    Boolean hidden;
    /**
     * 是否为版主
     */
    @JsonAlias("if_moderator")
    Boolean moderator;
    /**
     * 是否添加到末尾
     */
    @JsonAlias("modify_append")
    Boolean modifyAppend;
    /**
     * 回复id
     */
    @JsonAlias("pid")
    Long replyId;
    /**
     * 回复类型
     */
    @JsonAlias("post_type")
    Integer replyType;
    /**
     * 标题
     */
    @JsonAlias("subject")
    String title;
    /**
     * 主题id
     */
    @JsonAlias("tid")
    Long topicId;
    /**
     * 主题类型
     */
    @JsonAlias("type")
    Integer topicType;
    /**
     * 当前用户
     */
    @JsonAlias("__CU")
    CurrentUser currentUser;
    /**
     * 版面信息
     */
    @JsonAlias("__F")
    Forum forum;
    /**
     * 消耗说明
     */
    @JsonAlias("cost_info")
    String costInfo;

    public void setContent(String content) {
        this.content = StrUtils.decode(content);
    }

    public void setContentOriginal(String contentOriginal) {
        this.contentOriginal = StrUtils.decode(contentOriginal);
    }

    public void setTitle(String title) {
        this.title = title != null ? StringEscapeUtils.unescapeHtml4(title) : null;
    }

    /**
     * 回复状态
     *
     * @return 回复状态
     */
    public List<ReplyStatus> getReplyStatus() {
        return replyType == null ? null : ReplyStatus.parse(replyType);
    }

    /**
     * 主题状态
     *
     * @return 主题状态
     */
    public List<ReplyStatus> getTopicStatus() {
        return topicType == null ? null : ReplyStatus.parse(topicType);
    }


}
