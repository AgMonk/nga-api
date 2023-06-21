package com.gin.nga.utils;


import com.gin.jackson.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * QueryString工具
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/14 12:15
 */
public class QueryStringUtils {

    /**
     * 解析query string
     * @param qs query string
     * @return map
     */
    public static HashMap<String, Object> parse(String qs){
        if (ObjectUtils.isEmpty(qs)) {
            return new HashMap<>();
        }
        final HashMap<String, Object> map = new HashMap<>();
        //noinspection AlibabaUndefineMagicConstant
        for (String pair : qs.split("&")) {
            final String[] split = pair.split("=");
            final String key = split[0];
            final String value = split[1];
            if (!map.containsKey(key)) {
                map.put(key,value);
            }else{
                final Object item = map.get(key);
                if (item instanceof List<?>) {
                    List<?> list = (List<?>) item;
                    //noinspection unchecked
                    ((List<String>)list).add(value);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(String.valueOf(item));
                    list.add(value);
                    map.put(key,list);
                }
            }
        }
        return map;
    }
}   
