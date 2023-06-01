package com.gin.nga.enums;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 投票类型
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/1 10:01
 */
@RequiredArgsConstructor
public enum VoteType {
    /**
     * 投注(菠菜)
     */
    BET(1),

    ;

    public final int id;

    public static VoteType findById(int id){
        return Arrays.stream(values()).filter(i -> i.id == id).findFirst().orElse(null);
    }
}
