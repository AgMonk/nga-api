package com.gin.nga.params.nuke.mission;

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

    public MissionCheckInParam(long missionId) {
        super("checkin_count_add");
        this.missionId = missionId;
    }
}
