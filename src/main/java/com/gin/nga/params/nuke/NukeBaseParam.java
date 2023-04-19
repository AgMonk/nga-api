package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * nuke.php 基础参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 10:15
 */
@Setter
@Getter
public class NukeBaseParam {
    /**
     * 指令库名称
     */
    @JsonProperty("__lib")
    final String lib;
    /**
     * 操作名称
     */
    @JsonProperty("__act")
    final String act;

    public NukeBaseParam(String lib, String act) {
        this.lib = lib;
        this.act = act;
    }
}
