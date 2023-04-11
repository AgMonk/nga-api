package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 版面
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:53
 */
@Getter
@Setter
public class Forum {
    @JsonProperty("custom_level")
    List<CustomLevel> customLevels;
    /**
     * 版面名称
     */
    String name;
}   
