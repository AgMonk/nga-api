package com.gin.nga.params.forum;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.PageParam;
import lombok.Getter;

import java.io.Serializable;

/**
 * 搜索版面参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:24
 */
@Getter
public class ForumParam extends PageParam {
    /**
     * 搜索关键字
     */
    @JsonProperty("key")
    final String keyword;

    public ForumParam(String keyword) {
        this(keyword, null);
    }

    public ForumParam(String keyword, Serializable page) {
        super(page);
        this.keyword = keyword;
    }
}
