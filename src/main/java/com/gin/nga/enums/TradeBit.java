package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 交易货币类型
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 11:09
 */
@RequiredArgsConstructor
public enum TradeBit {
    /**
     * 铜币
     */
    COPPER(0),
    /**
     * N币
     */
    N(1),

    ;
    @JsonValue
    public final int id;

}
