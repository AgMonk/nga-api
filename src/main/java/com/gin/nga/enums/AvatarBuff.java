package com.gin.nga.enums;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 头像buff
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/24 16:22
 */
@RequiredArgsConstructor
public enum AvatarBuff {
    /**
     * 变派蒙
     */
    PAIMON (20,"https://img4.nga.178.com/ngabbs/face/a_paimon.png"),
    /**
     * 变羊
     */
    SHEEP (1,"https://img4.nga.178.com/ngabbs/face/a_sheep.png"),
    /**
     * 变POI
     */
    POI (7,"https://img4.nga.178.com/ngabbs/face/a_sheep_c.png"),
    ;


    public final long id;
    public final String url;

    public static AvatarBuff findById(long id){
        return Arrays.stream(values()).filter(i->i.id==id).findFirst().orElse(null);
    }
}
