package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.notice.NoticeStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * 提醒信息状态body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 16:56
 */
@Getter
@Setter
public class NoticeStatusBody {
    @JsonAlias("0")
    NoticeStatus data;
}   
