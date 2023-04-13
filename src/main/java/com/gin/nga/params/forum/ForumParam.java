package com.gin.nga.params.forum;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.PageParam;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 搜索版面参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:24
 */
@Getter
@Setter
public class ForumParam extends PageParam {
    /**
     * 搜索关键字
     */
    @JsonProperty("key")
    @NotEmpty
    String keyword;
}   
