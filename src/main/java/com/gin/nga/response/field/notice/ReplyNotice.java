package com.gin.nga.response.field.notice;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

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
     * todo 未知数据
     */
    @JsonAlias("0")
    Integer data;
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

}   
