package com.gin.nga.response.field.mission;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * 任务详情
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 12:47
 */
@Getter
@Setter
public class MissionDetail {
    @JsonAlias("1")
    Integer id;
}   
