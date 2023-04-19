package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.PageParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 收藏主题参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
public class FavorParam extends PageParam {
    /**
     * 收藏夹id
     */
    @JsonProperty("favor")
    final long folderId;

    public FavorParam() {
        this(null);
    }

    /**
     *  查询默认收藏夹
     * @param page 页码
     * @author bx002
     * @since 2023/4/19 14:45
     */
    public FavorParam(Serializable page) {
        this(1L, page);
    }
/**
 *  查询指定收藏夹
 * @param folderId 收藏夹id
 * @param page 页码
 * @author bx002
 * @since 2023/4/19 14:45
 */
    public FavorParam(long folderId, Serializable page) {
        super(page);
        this.folderId = folderId;
    }
}
