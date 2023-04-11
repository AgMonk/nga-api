package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 排序方式参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 17:55
 */
@RequiredArgsConstructor
public enum OrderByParam {
    /**
     * 按主题发布时间倒序
     */
    created("postdatedesc"),
    /**
     * 按最后回复时间倒序
     */
    replied("lastpostdesc"),
    ;
    @JsonValue
    public final String value;
}   
