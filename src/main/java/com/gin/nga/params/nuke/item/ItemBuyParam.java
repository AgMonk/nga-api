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
public class ItemBuyParam extends BaseItemParam {
    /**
     * 道具id
     */
    final int id;
    /**
     * 数量
     */
    final int count;

    public ItemBuyParam(int id, int count) {
        super("buy");
        this.id = id;
        this.count = count;
    }
}
