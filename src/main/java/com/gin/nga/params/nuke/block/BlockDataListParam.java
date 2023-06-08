package com.gin.nga.params.nuke.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * 查询 屏蔽用户/关键字
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/8 10:43
 */
@Getter
public class BlockDataListParam extends NukeBaseParam {
    @JsonProperty("uid")
    final long userId;

    public BlockDataListParam(long userId) {
        super("ucp", "get_block_word");
        this.userId = userId;
    }
}
