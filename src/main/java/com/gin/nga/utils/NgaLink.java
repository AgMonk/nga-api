package com.gin.nga.utils;

import com.gin.nga.enums.NgaLinkType;
import com.gin.nga.enums.NgaPhpApi;
import lombok.Getter;

import java.util.HashMap;

/**
 * 论坛链接
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/14 12:30
 */
@Getter
public class NgaLink {
    /**
     * 接口
     */
    NgaPhpApi ngaPhpApi;
    /**
     * 原始链接
     */
    String rawUrl;
    /**
     * 查询参数
     */
    HashMap<String, Object> queryParam;
    /**
     * 链接类型
     */
    NgaLinkType type;

    /**
     * 从url解析一个链接
     * @param url url
     */
    public NgaLink(String url) {
        this.rawUrl = url;
        this.ngaPhpApi = NgaPhpApi.find(url);

        final String s = "?";
        if (url.contains(s)) {
            // 查询参数
            final String qs = url.substring(url.indexOf(s) + 1);
            // query sting map
            this.queryParam =  QueryStringUtils.parse(qs);
        }

        this.type = NgaLinkType.find(this.ngaPhpApi,queryParam);
    }

    public Long findId(String key){
        final Object value = queryParam.get(key);
        if (value==null) {
            return null;
        }
        return Long.parseLong(String.valueOf(value));
    }
}
