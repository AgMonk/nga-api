package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.OrderByParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 版面主题浏览参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
public class ForumListParam extends ForumBaseParam {
    /**
     * 排序方式
     */
    @JsonProperty("order_by")
    OrderByParam orderBy;
}
