package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息中的声望字段,仅对自己查询时存在
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 16:18
 */
@Getter
@Setter
public class Reputation {
    @JsonAlias("0")
    String name;
    @JsonAlias("1")
    Integer value;
    @JsonAlias("2")
    String remark;
}   
