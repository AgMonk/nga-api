package com.gin.nga.bbscode.enums;

import lombok.RequiredArgsConstructor;

/**
 * 论坛标签名称(可识别标签名称列表)
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/29 14:14
 */
@RequiredArgsConstructor
public enum TagName {
    /**
     * 附件
     */
    attach("attach"),
    /**
     * 小标题
     */
    heading("h"),
    /**
     * 列表
     */
    list("list"),
    /**
     * 列表项目
     */
    li("*"),
    /**
     * 引用框
     */
    quote("quote"),
    /**
     * 随机块
     */
    randomblock("randomblock"),



    ;
    public final String name;
}
