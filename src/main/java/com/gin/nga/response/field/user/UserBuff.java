package com.gin.nga.response.field.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import com.gin.nga.enums.UserBuffType;
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
     * buff类型
     */
    @JsonAlias("1")
    UserBuffType type;
    /**
     * 释放buff的用户id
     */
    @JsonAlias("2")
    Long fromUserId;
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
     * 额外数据，可能为版面id，或道具的subType
     */
    @JsonAlias("6")
    Long extraData;
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

    public String getAvatarUrl(){
        if (type== UserBuffType.AVATAR_CHANGED) {
            if (extraData == 20L) {
                return "https://img4.nga.178.com/ngabbs/face/a_paimon.png";
            }
            if (extraData == 1L) {
                return "https://img4.nga.178.com/ngabbs/face/a_sheep.png";
            }
            if (extraData == 7L) {
                return "https://img4.nga.178.com/ngabbs/face/a_sheep_c.png";
            }
        }
        return null;
    }

}
