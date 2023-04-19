package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * 提醒消息body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 15:16
 */
@Getter
@Setter
public class NoticeBody {
    @JsonAlias("0")
    NoticeData data;
}   
