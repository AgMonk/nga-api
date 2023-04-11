package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 当前用户
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:47
 */
@Getter
@Setter
public class CurrentUser {
    @JsonProperty("admincheck")
    Integer adminCheck;
    @JsonProperty("group_bit")
    Long groupBit;
    /**
     * 威望
     */
    @JsonProperty("rvrc")
    Integer prestige;
    /**
     * 用户id
     */
    Long uid;
}   
