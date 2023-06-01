package com.gin.nga.response.vote;

import lombok.Getter;
import lombok.Setter;

/**
 * 菠菜选项
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/1 10:04
 */
@Getter
@Setter
public class BetOption extends VoteOption{
    /**
     * 金额(菠菜)
     */
    Long money;
    /**
     * 金额占比(菠菜)
     */
    double moneyPer;
}
