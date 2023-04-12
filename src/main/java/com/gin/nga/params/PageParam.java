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

    String page = "1";

    public void setPage(Serializable page) {
        final String p = String.valueOf(page);
        if (StrUtils.isNumber(p)){
            this.page = String.valueOf(Math.max(1, Integer.parseInt(p)));
        }else {
            this.page = "e";
        }
    }
}
