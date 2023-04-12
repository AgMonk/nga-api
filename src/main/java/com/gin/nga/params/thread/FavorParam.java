package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.PageParam;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
    @NotNull
    Long folderId;

    public FavorParam(int page) {
        super(page);
    }
}
