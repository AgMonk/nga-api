package com.gin.nga.params.nuke.pm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * 私信会话踢人
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 17:20
 */
@Getter
public class PmKickParam extends PmBaseParam{

    @JsonProperty("mid")
    final long messageId;
    /**
     * 目标用户uid
     */
    @JsonProperty("luid")
    final long userId;

    public PmKickParam(long messageId, long userId) {
        super("leave_topic");
        this.messageId = messageId;
        this.userId = userId;
    }
}
