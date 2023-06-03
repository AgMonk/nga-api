package com.gin.nga.response.field.user;

import com.gin.nga.enums.UserMoreInfoType;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户的更多信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 11:11
 */
@Getter
@Setter
public class UserMoreInfo {
    Long data;
    UserMoreInfoType type;
}   
