package com.gin.nga.params.nuke.item;

import lombok.Getter;

/**
 * 查询自己的道具列表 参数
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 09:52
 */
@Getter
public class ItemInventoryParam extends BaseItemParam {
    final Integer page;

    public ItemInventoryParam() {
        this(null);
    }

    public ItemInventoryParam(Integer page) {
        super("list");
        this.page = page;
    }
}
