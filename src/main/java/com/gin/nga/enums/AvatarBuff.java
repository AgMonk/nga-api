package com.gin.nga.enums;

import java.util.Arrays;

/**
 * 头像buff
 * 头像映射关系来自 : <a href="https://img4.nga.178.com/common_res/js_commonui.js?5871262">官方js</a>
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/24 16:22
 */
public enum AvatarBuff {
    /**
     * 变羊
     */
    SHEEP (1,"a_sheep.png"),
    /**
     * 变企鹅
     */
    PENGUIN(4 , "a_sheep_b.png"),
    /**
     * 变POI
     */
    POI (7,"a_sheep_c.png"),
    /**
     * 变咕哒
     */
    GUDA (11,"a_sheep_d.png"),
    /**
     * 变板鸭
     */
    BRONYA(12, "bronya1.png"),
    /**
     * 变咕哒
     */
    GUDA2(15, "a_sheep_e.png"),
    /**
     * 变刀客塔
     */
    doctor(17, "a_doc.png"),
    /**
     * 变派蒙
     */
    PAIMON (20,"a_paimon.png"),
    /**
     * 变鳄鱼,嘉维尔(明日方舟)
     */
    CROCODILE(19, "croco.png"),
    UNCLE(21, "a_uncle.png"),
    BRO(22, "a_bro.png"),
    UU(23, "a_uu.png"),

    ;

    public final long id;
    public final String url;

    AvatarBuff(long id, String url) {
        this.id = id;
        this.url = "https://img4.nga.178.com/ngabbs/face/"+  url;
    }

    public static AvatarBuff findById(long id){
        return Arrays.stream(values()).filter(i->i.id==id).findFirst().orElse(null);
    }
}
