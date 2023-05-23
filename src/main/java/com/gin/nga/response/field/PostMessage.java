package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 发帖响应
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 11:35
 */
@Getter
@Setter
public class PostMessage {
    public static final Pattern FORUM_PATTERN = Pattern.compile("_ff=(-*\\d+)");
    public static final Pattern REPLY_PATTERN = Pattern.compile("pid(\\d+)Anchor");
    public static final Pattern REPLY_PATTERN_2 = Pattern.compile("pid=(\\d+)");
    public static final Pattern TOPIC_PATTERN = Pattern.compile("tid=(\\d+)");
    @JsonAlias("1")
    String message;
    @JsonAlias("3")
    Integer code;
    @JsonAlias("4")
    String url;
    public Long getForumId() {
        final Matcher matcher = FORUM_PATTERN.matcher(url);
        if (matcher.find()) {
            return Long.parseLong(matcher.group(1));
        }
        return null;
    }

    public Long getReplyId() {
        {
            final Matcher matcher = REPLY_PATTERN.matcher(url);
            if (matcher.find()) {
                return Long.parseLong(matcher.group(1));
            }
        }
        {
            final Matcher matcher = REPLY_PATTERN_2.matcher(url);
            if (matcher.find()) {
                return Long.parseLong(matcher.group(1));
            }
        }
        return null;
    }

    public Long getTopicId() {
        final Matcher matcher = TOPIC_PATTERN.matcher(url);
        if (matcher.find()) {
            return Long.parseLong(matcher.group(1));
        }
        return null;
    }

}   
