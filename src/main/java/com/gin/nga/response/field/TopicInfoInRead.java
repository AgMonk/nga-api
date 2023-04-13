package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * read.php 接口中出现的主题信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:50
 */
@Getter
@Setter
public class TopicInfoInRead extends TopicInfo {
    /**
     * 合集信息(仅在合集内主题中出现)
     */
    @JsonProperty("__ST")
    TopicInfoInRead colTopic;
    @JsonProperty("digest")
    Integer digest;
    @JsonProperty("locked")
    Boolean locked;
    @JsonProperty("post_misc_var")
    Object postMiscVar;
    /**
     * 镜像到了某个主题
     */
    @JsonProperty("quote_to")
    Long quoteToTopicId;
    /**
     * 总楼层数(含主楼)
     */
    @JsonProperty("this_visit_rows")
    Integer repliesTotal;
}
