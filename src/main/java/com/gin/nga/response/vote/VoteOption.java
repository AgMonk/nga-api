package com.gin.nga.response.vote;

import lombok.Getter;
import lombok.Setter;

/**
 * 投票选项
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/1 10:04
 */
@Getter
@Setter
public class VoteOption {
    /**
     * id
     */
    int id;
    /**
     * 标题
     */
    String label;
    /**
     * 人数
     */
    int count;
    /**
     * 人数占比
     */
    double countPer;
}
