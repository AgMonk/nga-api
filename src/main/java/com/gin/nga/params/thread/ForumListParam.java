package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.OrderByParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

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
    final OrderByParam orderBy;

    public ForumListParam(Serializable page, OrderByParam orderBy, List<Long> forumId) {
        super(page, forumId);
        this.orderBy = orderBy;
    }

    public ForumListParam(Serializable page, OrderByParam orderBy, Long... forumId) {
        super(page, forumId);
        this.orderBy = orderBy;
    }
}
