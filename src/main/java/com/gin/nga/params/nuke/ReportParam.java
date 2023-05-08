package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * 举报参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 09:11
 */
@Getter
public class ReportParam extends NukeBaseParam {
    final int raw = 3;
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
    /**
     * 理由
     */
    @JsonProperty("info")
    final String reason;

    public ReportParam(long topicId, long replyId, String reason) {
        super("log_post", "report");
        this.topicId = topicId;
        this.replyId = replyId;
        this.reason = reason;
    }
}
