package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeFuncParam;
import lombok.Getter;

/**
 * 删除附件参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 15:55
 */
@Getter
public class DelAttachParam extends NukeFuncParam {
    /**
     * 附件文件名,url的最后一段
     */
    @JsonProperty("aid")
    private final  String filename;
    /**
     * 主题id
     */
    @JsonProperty("tid")
    private final long topicId;
    /**
     * 回复id
     */
    @JsonProperty("pid")
    private final long replyId;
    public DelAttachParam(long topicId, long replyId, String filename) {
        super("delattach");
        this.filename = filename;
        this.replyId = replyId;
        this.topicId = topicId;
    }
}
