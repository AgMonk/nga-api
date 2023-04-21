package com.gin.nga.params.nuke.mission;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 检查任务参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 10:50
 */
@Getter
@Setter
public class MissionCheckParam extends MissionBaseParam{
    /**
     * 任务id
     */
    @JsonProperty("mid")
    final long missionId;

    public MissionCheckParam(long missionId) {
        super("check_mission");
        this.missionId = missionId;
    }
}
