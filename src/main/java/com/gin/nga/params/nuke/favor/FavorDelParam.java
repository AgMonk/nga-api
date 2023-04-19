package com.gin.nga.params.nuke.favor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ListStringSerializer;
import com.gin.nga.params.nuke.NukeBaseParam;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 移除收藏参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 10:43
 */
@Getter
public class FavorDelParam extends NukeBaseParam {
    @JsonProperty("action")
    private final String action = "del";
    private final int raw = 3;
    @JsonProperty("tidarray")
    @JsonSerialize(using = ListStringSerializer.class)
    private final List<String> array = new ArrayList<>();
    private final int page;

    public FavorDelParam(int page) {
        super("topic_favor", "topic_favor");
        this.page = Math.max(1, page);
    }
    public FavorDelParam(int page,long topicId,long replyId) {
        super("topic_favor", "topic_favor");
        this.page = Math.max(1, page);
        this.add(topicId, replyId);
    }

    /**
     * 添加移除的收藏
     * @param topicId 主题id
     * @param replyId 回复id
     */
    public void add(long topicId,long replyId){
        this.array.add(String.format("%d_%d",topicId,replyId));
    }
    /**
     * 添加移除的收藏
     * @param topicId 主题id
     */
    public void add(long topicId){
        this.array.add(String.valueOf(topicId));
    }
}
