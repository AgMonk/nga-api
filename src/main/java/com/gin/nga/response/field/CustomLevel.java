package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义声望级别
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:52
 */
@Getter
@Setter
public class CustomLevel {
    @JsonProperty("r")
    Integer rank;

    @JsonProperty("n")
    String name;
}   
