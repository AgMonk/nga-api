package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.params.read.ReadReplyParam;
import com.gin.nga.response.ReplyLocation;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.regex.Matcher;

/**
 * @author bx002
 * @since 2024/1/20 13:47
 */
@Getter
@Setter
public class EditHistory {
    /**
     * 备份id
     */
    @JsonAlias("0")
    Long backupId;
    /**
     * 时间戳
     */
    @JsonAlias("1")
    ZonedDateTime timestamp;

    @JsonAlias("2")
    String url;

    @Nullable
    public ReadReplyParam obtainReadReplyParam() {
        long pid = 0;
        long tid = 0;

        {
            final Matcher matcher = ReplyLocation.REPLY_PATTERN_2.matcher(url);
            if (matcher.find()) {
                pid = Long.parseLong(matcher.group(1));
            }
        }
        {
            final Matcher matcher = ReplyLocation.TOPIC_PATTERN.matcher(url);
            if (matcher.find()) {
                tid = Long.parseLong(matcher.group(1));
            }
        }
        return new ReadReplyParam(tid,pid,backupId);
    }
}
