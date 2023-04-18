package com.gin.nga.response.field.notice;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * 赞踩变化提醒
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 10:07
 */
@Getter
@Setter
public class RecommendNotice extends BaseNotice {
    /**
     * todo 含义未明
     */
    @JsonAlias("0")
    Integer type;

    @JsonAlias("3")
    Long repliedUid;
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
     * todo 含义未明
     */
    @JsonAlias("10")
    Integer data;
}   
