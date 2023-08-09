package com.gin.nga.enums;

import com.gin.jackson.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
/**
 * 标题字体颜色
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/15 16:36
 */
@RequiredArgsConstructor
public enum TitleColor {
    /**
     * 红色
     */
    RED("红色","DD0000"),
    /**
     * 蓝色
     */
    BLUE("蓝色","0066BB"),
    /**
     * 绿色
     */
    GREEN("绿色","3D9F0E"),
    /**
     * 棕/橙色
     */
    ORANGE("棕/橙色","A06700"),
    /**
     * 银/灰色
     */
    SILVERY("银/灰色","C0C0C0"),
    /**
     * 默认
     */
    DEFAULT("默认","1A3959"),

    ;
    public final String name;
    public final String rgb;

    /**
     * 根据字符串中1出现的位置，返回颜色
     * @param s 字符串
     * @return 颜色
     */
    public static TitleColor find(String s){
        if (ObjectUtils.isEmpty(s)) {
            return DEFAULT;
        }
        final int i = s.indexOf("1");
        if (i>-1) {
            // 有颜色
            return values()[i];
        }
        return  DEFAULT;
    }
}
