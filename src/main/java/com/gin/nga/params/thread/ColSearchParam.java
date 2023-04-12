package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.BoolParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 合集主题搜索参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
public class ColSearchParam extends ColBaseParam {
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
