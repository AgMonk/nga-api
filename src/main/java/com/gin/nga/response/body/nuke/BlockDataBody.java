package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.BlockData;
import lombok.Getter;
import lombok.Setter;

/**
 * 屏蔽的用户和关键字
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/8 10:54
 */
@Getter
@Setter
public class BlockDataBody {
    @JsonAlias("0")
    BlockData data;
    /**
     * todo 含义未知，疑似为计数器上限
     */
    @JsonAlias("1")
    Integer limit;
    /**
     * 计数器，每修改一次会+1，会重置，重置条件不明
     */
    @JsonAlias("2")
    Integer count;

}   
