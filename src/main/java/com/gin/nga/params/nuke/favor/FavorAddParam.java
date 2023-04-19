package com.gin.nga.params.nuke.favor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.NukeBaseParam;
import lombok.Getter;

/**
 * 添加收藏参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 10:43
 */
@Getter
public class FavorAddParam extends NukeBaseParam {
    @JsonProperty("action")
    private final String action = "add";
    /**
     * 收藏夹id
     */
    @JsonProperty("folder")
    final long folderId;
    /**
     * 主题id
     */
    @JsonProperty("tid")
    final long topicId;
    /**
     * 回复id，若收藏主楼留空或填0
     */
    @JsonProperty("pid")
    final Long replyId;

    public FavorAddParam(long folderId, long topicId) {
        this(folderId, topicId, null);
    }

    public FavorAddParam(long folderId, long topicId, Long replyId) {
        super("topic_favor_v2", "add");
        this.folderId = folderId;
        this.topicId = topicId;
        this.replyId = replyId;
    }
}
