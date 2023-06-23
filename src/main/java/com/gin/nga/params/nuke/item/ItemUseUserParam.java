package com.gin.nga.params.nuke.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * 对用户使用道具 参数
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 09:52
 */
@Getter
public class ItemUseUserParam extends BaseItemParam {
    /**
     * 仓库中的道具id
     */
    final long id;
    /**
     * 目标用户id
     */
    @JsonProperty("arg")
    final int userId;

    public ItemUseUserParam(long id, int userId) {
        super("use");
        this.id = id;
        this.userId = userId;
    }
}
