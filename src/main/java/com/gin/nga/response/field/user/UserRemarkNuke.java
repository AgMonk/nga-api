package com.gin.nga.response.field.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * 用户备注(Nuke接口)
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/17 14:27
 **/
@Getter
@Setter
public class UserRemarkNuke {
    @JsonAlias("0")
    Long id;
    @JsonAlias("1")
    Long data1;
    @JsonAlias("2")
    String name;
    /**
     * 添加时间
     */
    @JsonAlias("3")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime timestamp;
    @JsonAlias("4")
    Long data4;
}
