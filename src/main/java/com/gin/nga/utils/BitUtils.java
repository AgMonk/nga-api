package com.gin.nga.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 位运算工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 12:50
 */
public class BitUtils {
    /**
     * 缩略图bit数据, 位置与后缀的对应关系
     */
    public static final Map<Integer,String> THUMB_MAP = new LinkedHashMap<Integer, String>(){{
        put(3,".thumb_ss.jpg");
        put(4,".thumb_s.jpg");
        put(5,".thumb.jpg");
        put(6,".medium.jpg");
    }};

    /**
 * bit解析为 0和1的字符串，每一位代表一个布尔值
 * @param bit bit
 * @return java.lang.String
 * @author bx002
 * @since 2023/4/21 12:51
 */
    public static String parse(int bit){
        return new StringBuilder(Integer.toBinaryString(bit)).reverse().toString();
    }

    /**
     * 生成缩略图地址
     * @param url 原图地址
     * @param thumbBit 缩略图bit
     * @return 缩略图地址
     */
    public static List<String> getThumbUrls(String url,int thumbBit){
        final ArrayList<String> list = new ArrayList<>();
        final String s = BitUtils.parse(thumbBit);
        final char trueValue = '1';
        THUMB_MAP.forEach((i,suffix)->{
            if (s.length()>i && s.charAt(i)==trueValue) {
                list.add(url+suffix);
            }
        });
        return list;
    }
}   
