package com.gin.nga.response.post;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.PostMessage;
import lombok.Getter;
import lombok.Setter;

/**
 * 发帖响应
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 11:34
 */
@Getter
@Setter
public class PostBody {
    /**
     * 消息
     */
    @JsonAlias("__MESSAGE")
    PostMessage data;
}   
