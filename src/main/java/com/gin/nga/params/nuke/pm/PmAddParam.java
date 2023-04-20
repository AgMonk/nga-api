package com.gin.nga.params.nuke.pm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ListLongSerializer;
import lombok.Getter;

import java.util.List;

/**
 * 私信添加参与者
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 17:02
 */
@Getter
public class PmAddParam extends PmBaseParam {
    @JsonProperty("mid")
    final long messageId;
    /**
     * 收信人
     */
    @JsonProperty("to")
    @JsonSerialize(using = ListLongSerializer.class)
    final List<Long> userId;

    public PmAddParam(long messageId, Long... userId) {
        this(messageId, List.of(userId));
    }

    public PmAddParam(long messageId, List<Long> userId) {
        super("add");
        this.messageId = messageId;
        this.userId = userId;
    }

}
