package com.gin.nga.response.alterlog;

import com.gin.nga.enums.ReputationLogType;
import lombok.Getter;
import lombok.Setter;

/**
 * 加分/扣分记录
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 17:56
 */
@Getter
@Setter
public class RewardLog extends ReputationLog {
    /**
     * 理由
     */
    String reason;

    public RewardLog(String[] split) {
        this.reputation = Integer.parseInt(split[0]);
        this.prestige = handlePrestige(split[1]);
        this.money = handleMoney(split[2]);
        this.type = ReputationLogType.reward;
        this.reason = split.length >= 5 ? split[4] : null;
    }
}
