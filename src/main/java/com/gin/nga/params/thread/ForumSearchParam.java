package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.deserializer.ListLongDeserializer;
import com.gin.jackson.serializer.BooleanJsonSerializer;
import com.gin.jackson.serializer.ListLongSerializer;
import com.gin.nga.params.PageParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 版面主题搜索参数
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
@NoArgsConstructor
public class ForumSearchParam extends PageParam {
    /**
     * 版面id
     */
    @JsonProperty("fid")
    @JsonSerialize(using = ListLongSerializer.class)
    @JsonDeserialize(using = ListLongDeserializer.class)
    List<Long> forumId;
    /**
     * 搜索关键字
     */
    @JsonProperty("key")
    String keyword;
    /**
     * 是否搜索主楼正文
     */
    @JsonProperty("content")
    @JsonSerialize(using = BooleanJsonSerializer.class)
    Boolean searchContent;
    /**
     * 是否只搜索精华帖
     */
    @JsonProperty("recommend")
    @JsonSerialize(using = BooleanJsonSerializer.class)
    Boolean recommendOnly;

    public ForumSearchParam(String keyword, Serializable page, Boolean searchContent, Boolean recommendOnly, List<Long> forumId) {
        super(page);
        this.keyword = keyword;
        this.searchContent = searchContent;
        this.recommendOnly = recommendOnly;
        this.forumId = forumId;
    }

    public ForumSearchParam(String keyword, Serializable page, Boolean searchContent, Boolean recommendOnly, Long... forumId) {
        this(keyword, page, searchContent, recommendOnly, Arrays.asList(forumId));
    }
}
