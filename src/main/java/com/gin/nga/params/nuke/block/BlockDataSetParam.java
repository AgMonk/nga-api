package com.gin.nga.params.nuke.block;

import com.gin.nga.params.nuke.base.NukeBaseParam;
import com.gin.nga.response.field.BlockData;
import lombok.Getter;

/**
 * 设置 屏蔽用户/关键字
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/8 10:43
 */
@Getter
public class BlockDataSetParam extends NukeBaseParam {
    final String data;
    
    public BlockDataSetParam(BlockData data) {
        super("ucp", "set_block_word");
        this.data = data.toQueryString();
    }
}
