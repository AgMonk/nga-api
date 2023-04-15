package com.gin.nga.utils;

/**
 * html代码工具
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/15 13:06
 */
public class HtmlUtils {
    public static String clearLinkBreak(String s){
        if (s==null) {
            return null;
        }
        return s.replace("\n","").replace("\r","");
    }
}   
