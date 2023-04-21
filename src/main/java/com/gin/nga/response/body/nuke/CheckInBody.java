package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.CheckInStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * 签到body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 16:34
 */
@Getter
@Setter
public class CheckInBody {
    /**
     * 消息
     */
    @JsonAlias("0")
    String message;
    /**
     * 签到状态
     */
    @JsonAlias("1")
    CheckInStatus status;

    @JsonAlias("2")
    Object missions;
}   
