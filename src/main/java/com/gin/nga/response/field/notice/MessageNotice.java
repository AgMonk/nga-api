package com.gin.nga.response.field.notice;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.enums.MessageNoticeType;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

/**
 * 私信提醒
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 10:01
 */
@Getter
@Setter
public class MessageNotice  extends  BaseNotice{
    /**
     * 类型
     */
    @JsonAlias("0")
    MessageNoticeType type;
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
     * 消息id
     */
    @JsonAlias("6")
    Long messageId;

    @Override
    public String getDescription() {
        switch (type) {
            case created:
                return String.format(Locale.CHINA, "%s 发起了会话", authorName);
            case replied:
                return String.format(Locale.CHINA, "%s 回复了会话", authorName);
            default:
                return "未知消息";
        }
    }
}
