package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.MissionBodyDeserializer;
import com.gin.nga.response.field.MissionInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 任务列表body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 09:40
 */
@Getter
@Setter
@JsonDeserialize(using = MissionBodyDeserializer.class)
public class MissionBody {
    List<MissionInfo> data;
}   
