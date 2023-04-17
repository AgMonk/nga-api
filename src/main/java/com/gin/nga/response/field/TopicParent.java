package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 合集中主题的上级信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 09:46
 */
@Getter
@Setter

public class TopicParent {
    /**
     * 版面id
     */
    @JsonProperty("forumId")
    @JsonAlias("0")
    Long forumId;
    /**
     * 合集主题id
     */
    @JsonProperty("colTid")
    @JsonAlias("1")
    Long colTid;
    /**
     * 版面/合集名称
     */
    @JsonProperty("name")
    @JsonAlias("2")
    String name;
}   
