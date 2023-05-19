package com.gin.nga.response.alterlog;

import com.gin.nga.enums.ReputationLogType;
import lombok.Getter;
import lombok.Setter;

/**
 * 取消操作记录
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 17:56
 */
@Getter
@Setter
public class CanceledLog extends ReputationLog {
    /**
     * 天数
     */
    public CanceledLog(String[] split ) {
        this.reputation = Integer.parseInt(split[0]);
        // todo 位置不确定
        this.prestige = Integer.parseInt(split[1]);
        this.money = Long.parseLong(split[2]);
        this.type = ReputationLogType.canceled;
    }
}
