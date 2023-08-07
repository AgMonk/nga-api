package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import com.gin.nga.enums.FollowStatusType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import java.time.ZonedDateTime;

/**
 * 关注动态
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/7 10:21
 **/
@Getter
@Setter
public class FollowStatus {
    /**
     * 动态id
     */
    @JsonAlias("0")
    long id;
    /**
     * 动态类型
     */
    @JsonAlias("1")
    FollowStatusType type;
    /**
     * 用户id 或 收藏者id
     */
    @JsonAlias("2")
    Long userId;
    /**
     * 主题id
     */
    @JsonAlias("3")
    Long topicId;
    /**
     * 回复id
     */
    @JsonAlias("4")
    Long replyId;
    /**
     * 发帖所回复的id 或 被收藏的主题作者uid
     */
    @JsonAlias("5")
    Long targetId;
    /**
     * 时间戳
     */
    @JsonAlias("6")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime timestamp;

    @JsonAlias("summary")
    String summary;

    public void setSummary(String summary) {
        this.summary = StringEscapeUtils.unescapeHtml4(summary);
    }
}
