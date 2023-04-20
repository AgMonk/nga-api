package com.gin.nga.params.nuke.pm;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * 获取短消息内容参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 15:12
 */
public class PrivateMessageReadParam extends PrivateMessageBaseParam {
    final int page;
    @JsonAlias("mid")
    final long messageId;
    /**
     *  参数
     * @param page 页码,-1表示最后一页
     * @param messageId 消息id
     * @author bx002
     * @since 2023/4/20 15:15
     */
    public PrivateMessageReadParam(long messageId, int page) {
        super("read");
        this.page = Math.max(-1, page);
        this.messageId = messageId;
    }
}
