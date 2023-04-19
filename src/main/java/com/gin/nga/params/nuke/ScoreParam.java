package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Setter;

/**
 * 赞踩参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 17:10
 */
@Setter
public class ScoreParam extends NukeBaseParam {
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
     * 1 = 赞 0 = 踩
     */
    @JsonProperty("value")
    final int agree;

    public ScoreParam(long topicId, long replyId, boolean agree) {
        super("topic_recommend", "add");
        this.topicId = topicId;
        this.replyId = replyId;
        this.agree = agree?1:0;
    }
}
