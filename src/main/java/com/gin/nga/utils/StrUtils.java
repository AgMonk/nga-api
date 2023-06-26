package com.gin.nga.utils;

import com.gin.common.utils.UnicodeUtils;
import org.apache.commons.text.StringEscapeUtils;

/**
 * 字符串工具类
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/26 15:59
 */
public class StrUtils {
    /**
     * 解码(反编译，unicode解码)
     * @param text 文本
     * @return 解码的文本
     */
    public static String decode(String text){
        if (text==null){
            return null;
        }
        text = StringEscapeUtils.unescapeHtml4(text);
        text = UnicodeUtils.decode(text);
        return text;
    }
}   
