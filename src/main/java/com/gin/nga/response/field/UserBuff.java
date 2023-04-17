package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * 用户身上的Buff
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 12:06
 */
@Getter
@Setter
public class UserBuff {
    /**
     * 存疑，可能是操作记录id
     */
    @JsonAlias("0")
    Long id;
    /**
     * 目前已知129与权限有关
     */
    @JsonAlias("1")
    Integer type;
    /**
     * 未知数据
     */
    @JsonAlias("2")
    Integer data2;
    /**
     * 用户id
     */
    @JsonAlias("3")
    Long userId;
    /**
     * buff开始时间
     */
    @JsonSerialize(using = ZdtJsonSerializer.class)
    @JsonAlias("4")
    ZonedDateTime start;
    /**
     * buff结束时间
     */
    @JsonSerialize(using = ZdtJsonSerializer.class)
    @JsonAlias("5")
    ZonedDateTime end;
    /**
     * 未知数据
     */
    @JsonAlias("6")
    Integer data6;
    /**
     * 未知数据
     */
    @JsonAlias("7")
    Integer data7;
    /**
     * 描述
     */
    @JsonAlias("9")
    String description;

}
