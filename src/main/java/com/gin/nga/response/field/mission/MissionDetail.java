package com.gin.nga.response.field.mission;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.nga.enums.MissionCounterType;
import com.gin.nga.enums.MissionDetailType;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

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

    @JsonAlias("4")
    Integer type;
    /**
     * 冷却时间(天)
     */
    @JsonAlias("5")
    Integer coolDownDays;

    @JsonAlias("6")
    Integer event;

    @JsonAlias("9")
    String name;

    @JsonAlias("10")
    String info;

    @JsonAlias("12")
    Long forumId;

    @JsonAlias("13")
    Long topicId;

    @JsonAlias("14")
    Long groupId;

    @JsonAlias("15")
    MissionCounterType counterType;

    @JsonAlias("20")
    Integer countGroup;

    @JsonAlias("21")
    Integer order;
    /**
     * 开始时间
     */
    @JsonAlias("22")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime startTime;
    /**
     * 结束时间
     */
    @JsonAlias("11")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime endTime;

    public List<MissionDetailType> getTypes(){
        return type==null?null: MissionDetailType.parse(type);
    }
}
