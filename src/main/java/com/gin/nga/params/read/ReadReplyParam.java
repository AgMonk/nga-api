package com.gin.nga.params.read;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 获取单个回复内容的参数
 * @author bx002
 * @since 2024/1/20 13:59
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ReadReplyParam {
    /**
     * 主题id
     */
    @JsonProperty("tid")
    final long topicId;
    /**
     * 回复ID
     */
    @JsonProperty("pid")
    final long replyId;
    /**
     * 备份ID
     */
    @JsonProperty("view_edit_bakup")
    Long backupId;
    /**
     * 预防字段名修改
     */
    @JsonProperty("view_edit_backup")
    Long backupId2;

    public ReadReplyParam(long topicId, long replyId, Long backupId) {
        this.topicId = topicId;
        this.replyId = replyId;
        this.backupId = backupId;
        this.backupId2 = backupId;
    }
}
