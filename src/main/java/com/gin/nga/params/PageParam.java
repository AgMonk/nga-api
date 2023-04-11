package com.gin.nga.params;

import lombok.Getter;

/**
 * 页码参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 17:25
 */
@Getter
public class PageParam {
    String page;
    public PageParam(int page) {
        this.page = page < 0 ? "e" : String.valueOf(page);
    }
}
