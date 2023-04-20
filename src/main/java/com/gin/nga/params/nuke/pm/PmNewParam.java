package com.gin.nga.params.nuke.pm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ListLongSerializer;
import lombok.Getter;

import java.util.List;

/**
 * 发起新私信参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 16:25
 */
@Getter
public class PmNewParam extends PmBaseParam {
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
    /**
     * 收信人
     */
    @JsonProperty("to")
    @JsonSerialize(using = ListLongSerializer.class)
    final List<Long> userId;

    public PmNewParam(String title, String content, List<Long> userId) {
        super("new");
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
    public PmNewParam(String title, String content, Long... userId) {
        this(title, content, List.of(userId));
    }
}
