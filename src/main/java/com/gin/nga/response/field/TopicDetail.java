package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * 主题详细信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:50
 */
@Getter
@Setter
public class TopicDetail extends TopicInfo {
    /**
     * 合集信息(仅在合集内主题中出现)
     */
    @JsonAlias("__ST")
    TopicDetail colTopic;
    @JsonAlias("digest")
    Integer digest;
    @JsonAlias("locked")
    Boolean locked;
    @JsonAlias("post_misc_var")
    Object postMiscVar;
    /**
     * 镜像到了某个主题
     */
    @JsonAlias("quote_to")
    Long quoteToTopicId;
    /**
     * 总楼层数(含主楼)
     */
    @JsonAlias("this_visit_rows")
    Integer repliesTotal;
}
