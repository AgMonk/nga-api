package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.CustomLevelDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 版面
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:53
 */
@Getter
@Setter
public class Forum {
    /**
     * 自定义声望等级(仅出现在主题内容接口)
     */
    @JsonProperty("custom_level")
    @JsonDeserialize(using = CustomLevelDeserializer.class)
    List<CustomLevel> customLevels;
    /**
     * 版面名称
     */
    @JsonProperty("name")
    String forumName;
    /**
     * 版面id
     */
    @JsonProperty("fid")
    Long forumId;
    /**
     * 合集标题
     */
    @JsonProperty("set_topic_subject")
    String colTitle;
    /**
     * 合集Tid
     */
    @JsonProperty("set_topic_tid")
    Long colTid;
    /**
     * 子版面数据
     */
    @JsonProperty("sub_forums")
            //todo 解析
    Map<String,Map<Long,Serializable>> subForums;
    /**
     * 置顶帖id
     */
    @JsonProperty("topped_topic")
    Long toppedTopicId;
}   
