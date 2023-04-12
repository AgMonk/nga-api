package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.nga.params.PageParam;
import com.gin.nga.serializer.IdSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 合集主题基础参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
public class ColBaseParam extends PageParam {
    /**
     * 合集主题Id
     */
    @JsonProperty("stid")
    @NotNull
    @JsonSerialize(using = IdSerializer.class)
    List<Long> colTid;

    public void setColTid(List<Long> colTid) {
        this.colTid = colTid;
    }

    public void setColTid(Long... colTid) {
        this.colTid = List.of(colTid);
    }
}
