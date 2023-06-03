package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.user.UserInfoNuke;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 15:20
 */
@Getter
@Setter
public class UserInfoBody {
    @JsonAlias("0")
    UserInfoNuke data;
}   
