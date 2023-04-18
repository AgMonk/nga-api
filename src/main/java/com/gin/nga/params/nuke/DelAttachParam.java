package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 删除附件参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 15:55
 */
@Getter
@RequiredArgsConstructor
public class DelAttachParam {
    private final String func = "delattach";
    private final int raw = 3;
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
    /**
     * 附件文件名,url的最后一段
     */
    @JsonProperty("aid")
    private final  String filename;

}   
