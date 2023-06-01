package com.gin.nga.enums;

import lombok.RequiredArgsConstructor;

/**
 * 域名
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 11:33
 */
@RequiredArgsConstructor
public enum NgaDomain {
    /**
     * <a href="https://ngabbs.com/">ngabbs</a>
     */
    com("https://ngabbs.com"),
    /**
     * <a href="https://bbs.nga.cn/">主站</a>
     */
    cn("https://bbs.nga.cn"),
    /**
     * <a href="https://nga.178.com/">178</a>
     */
    _178("https://nga.178.com")
    ;
    /**
     * 域名
     */
    public final String domain;
}
