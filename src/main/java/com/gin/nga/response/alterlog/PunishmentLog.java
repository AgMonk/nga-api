package com.gin.nga.response.alterlog;

import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.enums.ReputationLogType;
import lombok.Getter;
import lombok.Setter;

/**
 * 处罚记录
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 17:56
 */
@Getter
@Setter
public class PunishmentLog extends ReputationLog {
    /**
     * 理由
     */
    String reason;
    /**
     * 禁言天数
     */
    Integer days;
    /**
     * 版面id
     */
    Long forumId;

    public PunishmentLog(String[] split) {
        //L6 0 0 300 20 引战/转进/AOE
        this.days = Integer.parseInt(split[0]);
        this.forumId = "0".equals(split[1]) ? null : Long.parseLong(split[1]);
        this.reputation = Integer.parseInt(split[3]) * -1;
        this.prestige = Integer.parseInt(split[4]) * -1;
        this.money = handleMoney(split[2]);
        this.reason = split.length >= 6 ? split[5] : null;
        this.type = ReputationLogType.punishment;
        this.rate = Rate.negative;
    }

    @Override
    public String getDescription() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[处罚] ");
        if (forumId != null) {
            sb.append("在版面中");
        }
        sb.append("禁言 ");
        sb.append(days);
        sb.append("天 ");
        sb.append(super.getDescription());
        if (!ObjectUtils.isEmpty(reason)){
            sb.append("理由: ").append(reason);
        }
        return sb.toString();
    }
}
