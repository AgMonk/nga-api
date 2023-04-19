package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.BoolParam;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * 合集主题搜索参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
public class ColSearchParam extends ColBaseParam {
    /**
     * 搜索关键字
     */
    @JsonProperty("key")
    @NotNull
    final String keyword;
    /**
     * 是否搜索主楼正文
     */
    @JsonProperty("content")
    final BoolParam searchContent;
    /**
     * 是否只搜索精华帖
     */
    @JsonProperty("recommend")
    final BoolParam recommendOnly;

    public ColSearchParam(@NotNull String keyword,Serializable page, BoolParam searchContent, BoolParam recommendOnly, List<Long> colTid) {
        super(page, colTid);
        this.keyword = keyword;
        this.searchContent = searchContent;
        this.recommendOnly = recommendOnly;
    }

    public ColSearchParam(@NotNull String keyword,Serializable page, BoolParam searchContent, BoolParam recommendOnly, Long... colTid) {
        super(page, colTid);
        this.keyword = keyword;
        this.searchContent = searchContent;
        this.recommendOnly = recommendOnly;
    }
}
