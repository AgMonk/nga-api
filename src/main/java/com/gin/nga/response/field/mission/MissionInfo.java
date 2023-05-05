package com.gin.nga.response.field.mission;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

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

}   
