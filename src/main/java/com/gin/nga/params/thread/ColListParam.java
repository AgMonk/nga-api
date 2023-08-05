package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.OrderByParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 合集主题浏览参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
@NoArgsConstructor
public class ColListParam extends ColBaseParam {
    /**
     * 排序方式
     */
    @JsonProperty("order_by")
    OrderByParam orderBy;

    public ColListParam(long colTid, Serializable page, OrderByParam orderBy) {
        super(page, colTid);
        this.orderBy = orderBy;
    }

}
