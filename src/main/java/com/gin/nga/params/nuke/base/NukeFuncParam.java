package com.gin.nga.params.nuke.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * nuke.php 接口中含有func字段的参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 14:12
 */
@Getter
public class NukeFuncParam {
    private final int raw = 3;
    @JsonProperty("func")
    private  final String function;


    public NukeFuncParam(String function) {
        this.function = function;
    }
}
