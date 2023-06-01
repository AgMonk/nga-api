package com.gin.nga.enums;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 投票结果展示方式
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/1 10:01
 */
@RequiredArgsConstructor
public enum VoteResult {
    /**
     * 投票后查看结果
     */
    AFTER_VOTE(1),
    /**
     * 结束后查看结果
     */
    AFTER_END(2),
    ;

    public final int id;

    public static VoteResult findById(int id){
        return Arrays.stream(values()).filter(i -> i.id == id).findFirst().orElse(null);
    }
}
