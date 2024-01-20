package com.gin.nga.params.nuke.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询回复编辑历史参数
 * @author bx002
 * @since 2024/1/20 13:42
 */
@Getter
@Setter
public class NukeEditHistoryParam extends NukeBaseParam {
    public static final String LIB = "edit_history";
    /**
     * 主题id
     */
    @JsonProperty("tid")
    final long topicId;
    /**
     * 回复id
     */
    @JsonProperty("pid")
    final long replyId;

    @JsonProperty("page")
    final long page;


    public NukeEditHistoryParam(long topicId, long replyId, long page) {
        super(LIB,LIB);
        this.topicId = topicId;
        this.replyId = replyId;
        this.page = page;
    }
}
