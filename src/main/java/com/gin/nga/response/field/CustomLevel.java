package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
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
    @JsonProperty("rank")
    @JsonAlias("r")
    Integer rank;

    @JsonProperty("name")
    @JsonAlias("n")
    String name;
}   
