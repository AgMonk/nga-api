package com.gin.nga.response.alterlog;

import com.gin.nga.enums.ReputationLogType;
import lombok.Getter;
import lombok.Setter;

/**
 * 与声望/威望相关的操作记录
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 17:55
 */
@Setter
@Getter
public class ReputationLog {
    /**
     * 声望
     */
    Integer reputation;
    /**
     * 威望
     */
    Integer prestige;
    /**
     * 铜币
     */
    Long money;
    /**
     * 操作类型
     */
    ReputationLogType type;

    /**
     * 处理威望数字
     * @param s 威望字符串
     * @return 威望整数
     */
    public static Integer handlePrestige(String s){
        return (int) (Double.parseDouble(s)*10);
    }

    /**
     * 处理金币数字
     * @param s 金币字符串
     * @return 铜币整数
     */
    public static Long handleMoney(String s){
        return (long) (Double.parseDouble(s)*10000);
    }
}   
