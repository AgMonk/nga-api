package com.gin.nga.response.field;

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
    Long forumId;
    /**
     * 合集主题id
     */
    Long colTid;
    /**
     * 版面/合集名称
     */
    String name;
}   
