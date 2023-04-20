package com.gin.nga.params.nuke.pm;

import lombok.Getter;

/**
 * 获取私信列表参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 12:36
 */
@Getter
public class PmListParam extends PmBaseParam {
    final int page;

    public PmListParam(int page) {
        super("list");
        this.page = Math.max(1, page);
    }
}
