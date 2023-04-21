package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * 签到状态的Money字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 16:28
 */
@Getter
@Setter
public class CheckInMoney {
    /**
     * 用户id
     */
    @JsonAlias("uid")
    Long userId;

    @JsonAlias("money")
    Money money;
    /**
     * N币
     */
    @JsonAlias("money_n")
    Integer moneyN;
}   
