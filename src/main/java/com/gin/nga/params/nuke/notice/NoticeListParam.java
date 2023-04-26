package com.gin.nga.params.nuke.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 09:21
 */
@Getter
public class NoticeListParam extends NukeBaseParam {
    final int raw = 3;
    /**
     * 只返回时间戳大于该值的提醒
     */
    @JsonProperty("time_limit")
    final long timeLimit;

    public NoticeListParam(long timeLimit) {
        super("noti", "get_all");
        this.timeLimit = timeLimit;
    }
}
