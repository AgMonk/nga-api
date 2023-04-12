package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.BoolParam;
import com.gin.nga.params.PageParam;
import lombok.Getter;
import lombok.Setter;

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
     * 搜索关键字
     */
    @JsonProperty("key")
    String keyword;
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
}
