package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 赞踩参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 17:10
 */
@Setter
@Getter
public class RecommendParam extends NukeBaseParam {
    final int raw = 3;
    Integer value;
    /**
     * 主题id
     */
    @JsonProperty("tid")
    Long topicId;

    /**
     * 回复id
     */
    @JsonProperty("pid")
    Long replyId;
    public RecommendParam() {
        this.lib = "topic_recommend";
        this.act = "add";
    }
}
