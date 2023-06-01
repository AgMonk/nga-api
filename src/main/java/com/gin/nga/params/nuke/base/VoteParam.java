package com.gin.nga.params.nuke.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ListIntSerializer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/1 13:52
 */
@Getter
@Setter
public class VoteParam extends NukeBaseParam {
    final int raw = 3;
    @JsonProperty("tid")
    final long topicId;
    @JsonProperty("voteid")
    @JsonSerialize(using = ListIntSerializer.class)
    final List<Integer> id;

    public VoteParam(long topicId, List<Integer> id) {
        super("vote", "vote");
        this.topicId = topicId;
        this.id = id;
    }

    public VoteParam(long topicId, Integer... id) {
        this(topicId, List.of(id));
    }
}
