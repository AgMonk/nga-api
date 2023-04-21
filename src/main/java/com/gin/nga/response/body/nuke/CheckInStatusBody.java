package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.CheckInMoney;
import com.gin.nga.response.field.CheckInStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * 签到状态body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 16:02
 */
@Getter
@Setter
public class CheckInStatusBody {
    @JsonAlias("0")
    CheckInStatus status;
    @JsonAlias("1")
    CheckInMoney money;
    /**
     * 一句骚话
     */
    @JsonAlias("2")
    LinkedHashMap<Integer,String> message;
}
