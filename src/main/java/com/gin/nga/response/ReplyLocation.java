package com.gin.nga.response;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析回复的位置
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/3 10:39
 */
@Getter
@Setter
public class ReplyLocation {
    public static final Pattern PAGE_PATTERN = Pattern.compile("page=(\\d+)");
    public static final Pattern FORUM_PATTERN = Pattern.compile("_ff=(-*\\d+)");
    public static final Pattern REPLY_PATTERN = Pattern.compile("pid(\\d+)Anchor");
    public static final Pattern REPLY_PATTERN_2 = Pattern.compile("pid=(\\d+)");
    public static final Pattern TOPIC_PATTERN = Pattern.compile("tid=(\\d+)");
    Long forumId;
    Long topicId;
    Integer page;
    Long replyId;
    public ReplyLocation(String location) {
        {
            final Matcher matcher = REPLY_PATTERN.matcher(location);
            if (matcher.find()) {
                this.replyId = Long.parseLong(matcher.group(1));
            }
        }
        {
            final Matcher matcher = FORUM_PATTERN.matcher(location);
            if (matcher.find()) {
                this.forumId  = Long.parseLong(matcher.group(1));
            }
        }
        {
            final Matcher matcher = REPLY_PATTERN_2.matcher(location);
            if (matcher.find()) {
                this.replyId = Long.parseLong(matcher.group(1));
            }
        }
        {
            final Matcher matcher = TOPIC_PATTERN.matcher(location);
            if (matcher.find()) {
                this.topicId =  Long.parseLong(matcher.group(1));
            }
        }
        {
            final Matcher matcher = PAGE_PATTERN.matcher(location);
            if (matcher.find()) {
                this.page =  Integer.parseInt(matcher.group(1));
            }
        }
    }
}
