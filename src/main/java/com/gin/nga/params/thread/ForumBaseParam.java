package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ListLongSerializer;
import com.gin.nga.params.PageParam;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 版面主题基础参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
public class ForumBaseParam extends PageParam {
    /**
     * 版面id
     */
    @JsonProperty("fid")
    @NotEmpty
    @JsonSerialize(using = ListLongSerializer.class)
    List<Long> forumId;

    public void setForumId(List<Long> colTid) {
        this.forumId = colTid;
    }

    public void setForumId(Long... colTid) {
        this.forumId = List.of(colTid);
    }
}
