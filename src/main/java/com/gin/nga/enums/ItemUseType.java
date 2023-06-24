package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 道具使用方法
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/24 10:40
 */
@RequiredArgsConstructor
public enum ItemUseType {
    /**
     * 使用库存中的道具
     */
    USE_INVENTORY ("use"),
    /**
     * 从商店购买一个道具并使用
     */
    BUY_AND_USE ("buy_and_use"),
    ;
    @JsonValue
    public final String act;
}
