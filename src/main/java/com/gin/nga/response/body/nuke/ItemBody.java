package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.item.ItemData;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询商店道具body
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 10:13
 */
@Getter
@Setter
public class ItemBody {
    @JsonAlias("0")
    ItemData data;
}   
