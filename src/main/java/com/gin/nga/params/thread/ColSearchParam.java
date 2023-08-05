package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.BooleanJsonSerializer;
import com.gin.nga.deserializer.BooleanDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * 合集主题搜索参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
@NoArgsConstructor
public class ColSearchParam extends ColBaseParam {
    /**
     * 搜索关键字
     */
    @JsonProperty("key")
    @NotNull
     String keyword;
    /**
     * 是否搜索主楼正文
     */
    @JsonProperty("content")
    @JsonSerialize(using = BooleanJsonSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    Boolean searchContent;
    /**
     * 是否只搜索精华帖
     */
    @JsonProperty("recommend")
    @JsonSerialize(using = BooleanJsonSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    Boolean recommendOnly;

    public ColSearchParam(long colTid, @NotNull String keyword, Serializable page, Boolean searchContent, Boolean recommendOnly) {
        super(page, colTid);
        this.keyword = keyword;
        this.searchContent = searchContent;
        this.recommendOnly = recommendOnly;
    }

}
