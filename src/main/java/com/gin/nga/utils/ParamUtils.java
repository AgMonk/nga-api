package com.gin.nga.utils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 参数工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 17:07
 */
public class ParamUtils {
    public static HashMap<String, Serializable> pageParam(int page) {
        final HashMap<String, Serializable> map = new HashMap<>(1);
        map.put("page", page < 0 ? "e" : page);
        return map;
    }
}   
