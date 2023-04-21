package com.gin.nga.utils;

/**
 * 位运算工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 12:50
 */
public class BitUtils {
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
}   
