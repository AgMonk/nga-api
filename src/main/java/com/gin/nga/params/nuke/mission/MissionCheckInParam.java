package com.gin.nga.params.nuke.mission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 检查任务参数(签到类任务)
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 10:50
 */
@Getter
@Setter
public class MissionCheckInParam extends MissionBaseParam{
    /**
     * 任务id
     */
    @JsonProperty("mid")
    final long missionId;

    /**
     * 测试用,检查任务时不会自动完成任务
     */
    @JsonProperty("no_complete")
    @JsonIgnore
    final int noComplete = 1;


    public MissionCheckInParam(long missionId) {
        super("checkin_count_add");
        this.missionId = missionId;
    }
}
