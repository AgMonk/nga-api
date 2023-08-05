package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.OrderByParam;
import com.gin.nga.params.PageParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 版面主题浏览参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
@NoArgsConstructor
public class ForumListParam extends PageParam {
    /**
     * 版面id
     */
    @JsonProperty("fid")
    long forumId;
    /**
     * 排序方式
     */
    @JsonProperty("order_by")
    OrderByParam orderBy;

    public ForumListParam(long forumId, Serializable page, OrderByParam orderBy) {
        super(page);
        this.orderBy = orderBy;
        this.forumId = forumId;
    }

}
