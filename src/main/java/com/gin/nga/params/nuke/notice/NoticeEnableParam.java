package com.gin.nga.params.nuke.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeFuncParam;
import lombok.Getter;

/**
 * 关闭/开启某一楼层的回复、互动提醒参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 14:10
 */
@Getter
public class NoticeEnableParam extends NukeFuncParam {
    @JsonProperty("no_hint")
    private final int noHint;
    /**
     * 回复id
     */
    @JsonProperty("pid")
    private final long replyId;
    /**
     * 主题id
     */
    @JsonProperty("tid")
    private final long topicId;    /**
     * 参数
     * @param topicId 主题id
     * @param replyId 回复id
     * @param enable 是否开启提醒
     */
    public NoticeEnableParam(long topicId, long replyId, boolean enable) {
        super("noti_tag");
        this.noHint = enable ? 0 : 1;
        this.replyId = replyId;
        this.topicId = topicId;
    }
}
