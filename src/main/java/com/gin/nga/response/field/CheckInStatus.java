package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * 签到状态
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 16:08
 */
@Getter
@Setter
public class CheckInStatus {
    /**
     * 用户id
     */
    @JsonAlias("uid")
    Long userId;
    /**
     * 当前的连续签到天数(全局)
     */
    @JsonAlias("continued")
    Integer count;
    /**
     * 签到总天数
     */
    @JsonAlias("sum")
    Integer total;

    /**
     * 上次签到时间
     */
    @JsonAlias("last_time")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime lastTime;
    /**
     * 上次连续签到天数
     */
    @JsonAlias("last_continued")
    Integer lastContinued;
    /**
     * 上次签到是第x天
     */
    @JsonAlias("last_continued_day")
    Integer lastContinuedDay;
    /**
     * 上次签到是第x天
     */
    @JsonAlias("last_day")
    Integer lastDay;
    /**
     * 星期?
     */
    @JsonAlias("week")
    Integer week;
    /**
     * 现在是第x天
     */
    @JsonAlias("now_day")
    Integer nowDay;
}
