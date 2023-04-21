package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.MissionCheckInBodyDeserializer;
import lombok.Getter;
import lombok.Setter;

/**
 * 签到任务检查body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 11:14
 */
@Getter
@Setter
@JsonDeserialize(using = MissionCheckInBodyDeserializer.class)
public class MissionCheckInBody {
}
