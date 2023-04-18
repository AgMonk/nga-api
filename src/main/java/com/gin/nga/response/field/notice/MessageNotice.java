package com.gin.nga.response.field.notice;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * 短消息提醒
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 10:01
 */
@Getter
@Setter
public class MessageNotice  extends  BaseNotice{
    /**
     * 作者uid
     */
    @JsonAlias("1")
    Long authorId;
    /**
     * 作者用户名
     */
    @JsonAlias("2")
    String authorName;
    /**
     * 被回复人uid
     */
    @JsonAlias("6")
    Long messageId;
}
