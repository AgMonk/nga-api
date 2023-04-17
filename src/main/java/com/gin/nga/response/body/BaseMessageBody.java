package com.gin.nga.response.body;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 消息对象
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 17:19
 */
@Getter
@Setter
public class BaseMessageBody {
    /**
     * 提示消息
     */
    @JsonProperty("message")
    @JsonAlias("0")
    String message;
}   
