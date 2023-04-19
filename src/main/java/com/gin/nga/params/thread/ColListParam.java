package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.OrderByParam;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * 合集主题浏览参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
public class ColListParam extends ColBaseParam {
    /**
     * 排序方式
     */
    @JsonProperty("order_by")
    final OrderByParam orderBy;

    public ColListParam(Serializable page, OrderByParam orderBy, List<Long> colTid) {
        super(page, colTid);
        this.orderBy = orderBy;
    }

    public ColListParam(Serializable page, OrderByParam orderBy, Long... colTid) {
        super(page, colTid);
        this.orderBy = orderBy;
    }
}
