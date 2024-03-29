package com.gin.nga.params.nuke.item;

import com.gin.nga.params.nuke.base.NukeFuncParam;
import lombok.Getter;

/**
 * 查询自己的道具列表 参数
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 09:52
 */
@Getter
public class BaseItemParam extends NukeFuncParam {
    final String act;
    public BaseItemParam(String act) {
        super("item");
        this.act = act;
    }
}
