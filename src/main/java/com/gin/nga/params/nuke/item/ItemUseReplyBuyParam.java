package com.gin.nga.params.nuke.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

/**
 * 购买一个道具，对回复使用 参数
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 15:36
 */
@Getter
public class ItemUseReplyBuyParam extends BaseItemParam {
    /**
     * 商店中的道具id
     */
    final long id;
    /**
     * 主题id
     */
    @JsonIgnore
    final long topicId;
    /**
     * 回复id
     */
    @JsonIgnore
    final long replyId;
    /**
     * true = 支持 , false = 反对 , null = 中立
     */
    @JsonIgnore
    final Boolean recommend;

    public ItemUseReplyBuyParam(long id, long topicId, long replyId, Boolean recommend) {
        super("buy_and_use");
        this.id = id;
        this.topicId = topicId;
        this.replyId = replyId;
        this.recommend = recommend;
    }

    public String getArg() {
        final int r = recommend == null ? 0 : (recommend ? 1 : -1);
        return topicId + "\t" + replyId + "\t" + r;
    }
}
