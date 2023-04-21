package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.MissionCheckBodyDeserializer;
import com.gin.nga.response.field.mission.MissionInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * 任务检查body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 11:14
 */
@Getter
@Setter
@JsonDeserialize(using = MissionCheckBodyDeserializer.class)
public class MissionCheckBody {
    /**
     * 是否完成
     */
    boolean completed;
    /**
     * 任务信息
     */
    MissionInfo missionInfo;
    /**
     * 奖励消息
     */
    String message;
}
