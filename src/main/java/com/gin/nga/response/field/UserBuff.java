package com.gin.nga.response.field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.common.utils.TimeUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;

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
    Long id;
    /**
     * 目前已知129与权限有关
     */
    Integer type;
    /**
     * 未知数据
     */
    Integer data2;
    /**
     * 用户id
     */
    Long userId;
    /**
     * buff开始时间
     */
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime start;
    /**
     * buff结束时间
     */
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime end;
    /**
     * 未知数据
     */
    Integer data6;
    /**
     * 未知数据
     */
    Integer data7;
    /**
     * 描述
     */
    String description;

    public UserBuff(LinkedHashMap<Integer, Serializable> map) {
        this.id = parsLong(map, 0);
        this.type = parsInt(map, 1);
        this.data2 = parsInt(map, 2);
        this.userId = parsLong(map, 3);
        this.start = parsZdt(map, 4);
        this.end = parsZdt(map, 5);
        this.data6 = parsInt(map, 6);
        this.data7 = parsInt(map, 7);
        this.description = parsString(map, 9);
    }

    private static Integer parsInt(LinkedHashMap<Integer, Serializable> map, int pos) {
        return Integer.parseInt(parsString(map, pos));
    }

    private static Long parsLong(LinkedHashMap<Integer, Serializable> map, int pos) {
        return Long.parseLong(parsString(map, pos));
    }

    private static String parsString(LinkedHashMap<Integer, Serializable> map, int pos) {
        return String.valueOf(map.get(pos));
    }

    private static ZonedDateTime parsZdt(LinkedHashMap<Integer, Serializable> map, int pos) {
        final Long time = parsLong(map, pos);
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(time), TimeUtils.CHINESE_ZONE_ID);
    }

}
