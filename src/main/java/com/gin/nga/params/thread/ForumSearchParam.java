package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.BooleanJsonSerializer;
import com.gin.common.serializer.ListLongSerializer;
import com.gin.nga.params.PageParam;
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
public class ForumSearchParam extends PageParam {
    /**
     * 版面id
     */
    @JsonProperty("fid")
    @NotEmpty
    @JsonSerialize(using = ListLongSerializer.class)
    final List<Long> forumId;
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
    @JsonSerialize(using = BooleanJsonSerializer.class)
    final Boolean searchContent;
    /**
     * 是否只搜索精华帖
     */
    @JsonProperty("recommend")
    @JsonSerialize(using = BooleanJsonSerializer.class)
    final Boolean recommendOnly;

    public ForumSearchParam(String keyword, Serializable page, Boolean searchContent, Boolean recommendOnly, List<Long> forumId) {
        super(page);
        this.keyword = keyword;
        this.searchContent = searchContent;
        this.recommendOnly = recommendOnly;
        this.forumId = forumId;
    }

    public ForumSearchParam(String keyword, Serializable page, Boolean searchContent, Boolean recommendOnly, Long... forumId) {
       this(keyword, page, searchContent, recommendOnly, List.of(forumId));
    }
}
