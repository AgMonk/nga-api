package com.gin.nga.response.field.notice;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.enums.ReplyNoticeType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

/**
 * 回复提醒
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 09:30
 */
@Getter
@Setter
public class ReplyNotice  extends  BaseNotice {
    /**
     * 类型
     */
    @JsonAlias("0")
    ReplyNoticeType type;
    /**
     * 作者uid
     */
    @JsonAlias("1")
    Long authorId;
    /**
     * 作者用户名
     */
    @JsonAlias("2")
    String authorName;
    /**
     * 被回复人uid
     */
    @JsonAlias("3")
    Long repliedUid;
    /**
     * 被回复人用户名
     */
    @JsonAlias("4")
    String repliedName;
    /**
     * 主题标题
     */
    @JsonAlias("5")
    String topicTitle;
    /**
     * 主题id
     */
    @JsonAlias("6")
    Long topicId;
    /**
     * 回复id
     */
    @JsonAlias("7")
    Long replyId;
    /**
     * 被回复的id
     */
    @JsonAlias("8")
    Long repliedId;
    /**
     * 页码
     */
    @JsonAlias("10")
    Integer page;

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = StringEscapeUtils.unescapeHtml4(topicTitle);
    }
}
