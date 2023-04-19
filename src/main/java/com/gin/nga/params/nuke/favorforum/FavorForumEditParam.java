package com.gin.nga.params.nuke.favorforum;

import com.gin.nga.enums.FavorAction;
import com.gin.nga.enums.FavorType;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 14:06
 */
public class FavorForumEditParam extends FavorForumParam {
    /**
     * 修改参数
     * @param action 操作类型
     * @param type   收藏类型
     * @param id     id
     */
    public FavorForumEditParam(FavorAction action, FavorType type, long id) {
        super(action, type, id);
    }
}
