package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 道具类型
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 10:59
 */
@RequiredArgsConstructor
public enum ItemType {
    ACTIVATION_CODE(1,"激活码"),
    MEDAL(2,"徽章"),
    TO_USER(3,"对人"),
    TO_REPLY(5,"对贴"),
    ;
    @JsonValue
    public final int id;
    public final String name;

    public static ItemType findById(int id){
        return Arrays.stream(values()).filter(i -> i.id == id).findFirst().orElse(null);
    }
}
