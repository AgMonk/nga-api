package com.gin.nga.params.thread;

import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/10 17:43
 */
@NoArgsConstructor
public class ForumRecommendParam extends ForumSearchParam{
    public ForumRecommendParam(long forumId, Serializable page) {
        super(null, page, null, true, forumId);
    }
}
