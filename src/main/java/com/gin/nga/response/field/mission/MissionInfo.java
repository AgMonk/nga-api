package com.gin.nga.response.field.mission;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.common.utils.TimeUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * 任务信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 09:33
 */
@Getter
@Setter
public class MissionInfo {
    /**
     * id
     */
    @JsonAlias("id")
    Integer id;
    /**
     * 名称
     */
    @JsonAlias("name")
    String name;
    /**
     * 任务的文字描述
     */
    @JsonAlias("info")
    String info;
    /**
     * 详情
     */
    @JsonAlias("detail")
    String detail;
    /**
     * 状态
     */
    @JsonAlias("stat")
    String status;

    /**
     * 任务详情
     */
    @JsonAlias("raw_detail")
    MissionDetail missionDetail;
    /**
     * 任务状态
     */
    @JsonAlias("raw_stat")
    MissionStatus missionStatus;

    /**
     * 是否可以签到
     * @return null = 不可，true = 可以签到， false=已签到
     */
    public Boolean isCheckAble(){
        if (missionStatus==null) {
            return null;
        }
        final ZonedDateTime lastCountTime = missionStatus.getLastCountTime();
        if (lastCountTime==null) {
            return true;
        }
        // 今天
        String today = TimeUtils.format(ZonedDateTime.now(), TimeUtils.DATE_FORMATTER,TimeUtils.CHINESE_ZONE_ID);
        // 上次签到时间
        String lastDay = TimeUtils.format(lastCountTime, TimeUtils.DATE_FORMATTER);

        return  !today.equals(lastDay);
    }
}   
