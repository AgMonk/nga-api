package com.gin.nga.response.field.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import com.gin.nga.deserializer.BooleanDeserializer;
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
public class UserRemarkRead {
    @JsonAlias("0")
    Long id;
    /**
     * 添加备注的用户id
     */
    @JsonAlias("1")
    Long userId;
    /**
     * 添加时间
     */
    @JsonAlias("2")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime timestamp;
    @JsonAlias("3")
    @JsonDeserialize(using = BooleanDeserializer.class)
    boolean isPublic;
    @JsonAlias("4")
    String name;
}
