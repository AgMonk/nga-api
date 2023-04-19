package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.BoolParam;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 版面主题搜索参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
public class ForumSearchParam extends ForumBaseParam {
    /**
     * 搜索关键字
     */
    @JsonProperty("key")
    @NotEmpty
    final String keyword;
    /**
     * 是否搜索主楼正文
     */
    @JsonProperty("content")
    BoolParam searchContent;
    /**
     * 是否只搜索精华帖
     */
    @JsonProperty("recommend")
    BoolParam recommendOnly;

    public ForumSearchParam(String keyword, Serializable page, BoolParam searchContent, BoolParam recommendOnly, List<Long> forumId) {
        super(page, forumId);
        this.keyword = keyword;
        this.searchContent = searchContent;
        this.recommendOnly = recommendOnly;
    }

    public ForumSearchParam(String keyword, Serializable page, BoolParam searchContent, BoolParam recommendOnly, Long... forumId) {
        super(page, forumId);
        this.keyword = keyword;
        this.searchContent = searchContent;
        this.recommendOnly = recommendOnly;
    }
}
