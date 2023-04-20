package com.gin.nga.params.nuke.pm;

import lombok.Getter;

/**
 * 私信黑名单查询参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 17:29
 */
@Getter
public class PmBlockListParam extends PmBaseParam{
    public PmBlockListParam() {
        super("list_block");
    }
}
