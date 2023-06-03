package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.ReplyLocation;
import lombok.Getter;
import lombok.Setter;

/**
 * 发帖响应
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 11:35
 */
@Getter
@Setter
public class PostMessage {
    @JsonAlias("1")
    String message;
    @JsonAlias("3")
    Integer code;
    @JsonAlias("4")
    ReplyLocation location;
}
