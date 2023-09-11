package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.notice.MessageNotice;
import com.gin.nga.response.field.notice.RecommendNotice;
import com.gin.nga.response.field.notice.ReplyNotice;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 提醒消息body
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 09:24
 */
@Getter
@Setter
public class NoticeData {
    @JsonAlias("0")
    List<ReplyNotice> replyNotices;
    @JsonAlias("1")
    List<MessageNotice> msgNotices;
    @JsonAlias("2")
    List<RecommendNotice> recommendNotices;
    /**
     * 获得时间
     */
    @JsonAlias("lasttime")
    ZonedDateTime timestamp;

    /**
     * 下一次执行请求带的time参数
     */
    long nextTime;
}
