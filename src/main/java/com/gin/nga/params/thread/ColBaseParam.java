package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ListLongSerializer;
import com.gin.nga.params.PageParam;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

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
    @NotEmpty
    @JsonSerialize(using = ListLongSerializer.class)
    final List<Long> colTid;

    public ColBaseParam(Serializable page, List<Long> colTid) {
        super(page);
        this.colTid = colTid;
    }
    public ColBaseParam(Serializable page, Long... colTid) {
        this(page,List.of(colTid));
    }
}
