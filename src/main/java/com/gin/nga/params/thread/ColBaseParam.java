package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ListLongSerializer;
import com.gin.nga.params.PageParam;
import lombok.Getter;

import java.io.Serializable;

/**
 * 合集主题基础参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
public class ColBaseParam extends PageParam {
    /**
     * 合集主题Id
     */
    @JsonProperty("stid")
    @JsonSerialize(using = ListLongSerializer.class)
    final long colTid;

    public ColBaseParam(Serializable page, long colTid) {
        super(page);
        this.colTid = colTid;
    }
}
