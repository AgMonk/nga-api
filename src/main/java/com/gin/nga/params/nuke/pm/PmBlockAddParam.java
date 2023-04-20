package com.gin.nga.params.nuke.pm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * 私信黑名单添加参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 17:29
 */
@Getter
public class PmBlockAddParam extends PmBaseParam{
    @JsonProperty("buids")
    final long userId;
    public PmBlockAddParam(long userId) {
        super("add_block");
        this.userId = userId;
    }
}
