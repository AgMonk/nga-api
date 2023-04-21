package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.CheckInStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * 签到状态body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 16:02
 */
@Getter
@Setter
public class CheckInBody {
    @JsonAlias("0")
    CheckInStatus status;
}   
