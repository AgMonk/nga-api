package com.gin.nga.bbscode.utils;

import org.jetbrains.annotations.NonNls;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * 正则表达式工厂
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/29 14:58
 */
public class PatternFactory {
    public static final HashMap<String, Pattern> MAP = new HashMap<>();
    public static Pattern getPattern(@NonNls String regex){
        if (MAP.containsKey(regex)){
            return MAP.get(regex);
        }
        final Pattern pattern = Pattern.compile(regex);
        MAP.put(regex, pattern);
        return pattern;
    }
}   
