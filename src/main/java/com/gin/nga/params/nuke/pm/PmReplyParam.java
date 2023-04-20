package com.gin.nga.params.nuke.pm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * 回复私信
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 16:53
 */
@Getter
public class PmReplyParam extends PmBaseParam {
    /**
     * 消息id
     */
    @JsonProperty("mid")
    final long messageId;
    /**
     * 标题
     */
    @JsonProperty("subject")
    final String title;
    /**
     * 正文
     */
    @JsonProperty("content")
    final String content;
    public PmReplyParam(long messageId, String title, String content) {
        super("reply");
        this.messageId = messageId;
        this.title = title;
        this.content = content;
    }
}
