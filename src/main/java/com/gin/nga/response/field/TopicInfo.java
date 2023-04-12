package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.nga.serializer.ZdtJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * 主题信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 11:27
 */
@Getter
@Setter
public class TopicInfo {
    /**
     * 作者用户名
     */
    @JsonProperty("author")
    String authorName;
    /**
     * 作者uid
     */
    @JsonProperty("authorid")
    Long authorUid;
    /**
     * 版面id
     */
    @JsonProperty("fid")
    Long forumId;
    @JsonProperty("jdata")
    String jData;
    /**
     * 最后改动时间
     */
    @JsonProperty("lastmodify")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime lastModify;
    /**
     * 最后回复时间
     */
    @JsonProperty("lastpost")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime lastPost;
    /**
     * 最后回复的用户名
     */
    @JsonProperty("lastposter")
    String lastPosterName;
    /**
     * 所属合集信息
     */
    @JsonProperty("parent")
    Map<Long, Serializable> parentInfo;
    /**
     * 发表时间
     */
    @JsonProperty("postdate")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime postDatetime;
    @JsonProperty("quote_from")
    Long quoteFrom;
    /**
     * 推荐分
     */
    @JsonProperty("recommend")
    Integer recommendScore;
    /**
     * 回复数
     */
    @JsonProperty("replies")
    Integer replies;
    /**
     * 主题标题
     */
    @JsonProperty("subject")
    String title;
    /**
     * 主题tid
     */
    @JsonProperty("tid")
    Long tid;
    /**
     * 主题标题字体信息
     */
    @JsonProperty("topic_misc")
    String topicMisc;
    /**
     * 额外信息
     */
    @JsonProperty("topic_misc_var")
    Map<Long, Serializable> topicMiscVar;
    /**
     * 主题url
     */
    @JsonProperty("tpcurl")
    String url;
    /**
     * 类型
     */
    @JsonProperty("type")
    Long type;
}
