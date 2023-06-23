package com.gin.nga.params.nuke.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ListIntSerializer;
import com.gin.nga.enums.ItemType;
import lombok.Getter;

import java.util.List;

/**
 * 查询商店里的道具列表 参数
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 09:52
 */
@Getter
public class ItemStoreParam extends BaseItemParam {
    final Integer page;
    /**
     * 类别id
     */
    final ItemType type;
    /**
     * 子类别
     */
    @JsonProperty("sub_type")
    @JsonSerialize(using = ListIntSerializer.class)
    final List<Integer> subType;

    public ItemStoreParam() {
        this(null, null, null);
    }

    public ItemStoreParam(Integer page, ItemType type, List<Integer> subType) {
        super("store");
        this.page = page;
        this.type = type;
        this.subType = subType;
    }
}
