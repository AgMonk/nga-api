package com.gin.nga.params;

import com.gin.common.utils.StrUtils;
import lombok.Getter;

import java.io.Serializable;

/**
 * 页码参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 17:25
 */
@Getter
public class PageParam {
    /**
     * 页码
     */
    final String page;

    public PageParam(Serializable page) {
        if (page == null) {
            this.page = "1";
            return;
        }
        final String p = String.valueOf(page);
        if (StrUtils.isNumber(p)) {
            this.page = String.valueOf(Math.max(1, Integer.parseInt(p)));
        } else {
            this.page = "e";
        }
    }

    public PageParam() {
        this(null);
    }
}
