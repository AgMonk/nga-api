package com.gin.nga.enums;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

/**
 * php路径
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 14:37
 */
@RequiredArgsConstructor
public enum NgaPhpApi {
    /**
     * 查询主题
     */
    thread("thread.php"),
    /**
     * 查询主题内容
     */
    read("read.php"),
    /**
     * 综合操作
     */
    nuke("nuke.php"),
    /**
     * 版面操作
     */
    forum("forum.php"),
    /**
     * 回复
     */
    post("post.php"),
    ;


    public  final String path;

    public static NgaPhpApi find(String s){
        if (ObjectUtils.isEmpty(s)) {
            return null;
        }
        for (NgaPhpApi api : values()) {
            if (s.contains(api.path)) {
                return api;
            }
        }
        return null;
    }
}
