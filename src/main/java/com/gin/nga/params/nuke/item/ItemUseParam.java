package com.gin.nga.params.nuke.item;

import com.gin.nga.enums.ItemUseType;
import lombok.Getter;

/**
 * 使用道具参数
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/24 10:41
 */
@Getter
public class ItemUseParam extends BaseItemParam {
    final long id;
    final String arg;

    public ItemUseParam(ItemUseType type, long id, String arg) {
        super(type.act);
        this.id = id;
        this.arg = arg;
    }

    /**
     * 对用户使用
     * @param type   使用类型
     * @param id     库存中的道具id
     * @param userId 目标用户id
     */
    public ItemUseParam(ItemUseType type, long id, long userId) {
        super(type.act);
        this.id = id;
        this.arg = String.valueOf(userId);
    }

    /**
     * 对回复使用
     * @param type 使用类型
     * @param id 商店中的道具id
     * @param topicId 主题id
     * @param replyId 回复id
     * @param recommend true = 支持 , false = 反对 , null = 中立
     */
    public ItemUseParam(ItemUseType type, long id, long topicId, long replyId, Boolean recommend) {
        super(type.act);
        final int r = recommend == null ? 0 : (recommend ? 1 : -1);
        this.id = id;
        this.arg = topicId + "\t" + replyId + "\t" + r;

    }
}
