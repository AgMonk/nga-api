package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * 屏蔽的子版面body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 15:57
 */
@Getter
@Setter
public class BlockSubForum {
    @JsonProperty("union_fid")
    LinkedHashMap<Integer,Object> unionFid;

    @JsonProperty("block_tid")
    LinkedHashMap<Integer,Long> blockTopicId;

    public static class Res extends LinkedHashMap<Integer, BlockSubForum>{}
}
