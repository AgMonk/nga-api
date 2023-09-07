package com.gin.nga.response.field.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import com.gin.nga.enums.UserBuffType;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * 版面禁言描述的匹配正则
     */
    public static final Pattern PATTERN_MUTE_FORUM = Pattern.compile("<b>(.+?)\\((\\d+)\\)</b>");
    /**
     * 存疑，可能是操作记录id
     */
    @JsonAlias("0")
    Long id;
    /**
     * buff类型id 已知类型的buff:  {@link  UserBuffType}
     */
    @JsonAlias("1")
    Integer typeId;
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

    public void setDescription(String description) {
        this.description = description;

        // 裁剪多余信息
        if (this.description!=null) {
            final int index = this.description.indexOf("持续至");
            if (index>-1){
                this.description = this.description.substring(0,index);
            }
        }
    }

    /**
     * 被禁言的版面名称
     * @return 版面名称
     */
    public String getForumName(){
        if (description == null) {
            return null;
        }
        final Matcher matcher = PATTERN_MUTE_FORUM.matcher(description);
        if (matcher.find()){
            return matcher.group(1);
        }else{
            return null;
        }
    }
}
