package com.gin.nga.response.field.mission;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * 任务状态
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 13:48
 */
@Getter
@Setter
public class MissionStatus {
    /**
     * 连续签到次数
     */
    @JsonAlias("1")
    Integer count;
    /**
     * 总签到次数
     */
    @JsonAlias("2")
    Integer total;
    /**
     * 完成时间
     */
    @JsonAlias("3")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime completedTime;
    /**
     * 上一次计数时间
     */
    @JsonAlias("4")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime lastCountTime;
    /**
     * 任务自定义计数
     */
    @JsonAlias("5")
    Integer customCounter;
    /**
     * 本次计数的开始时间
     */
    @JsonAlias("7")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime thisCountTime;

}   
