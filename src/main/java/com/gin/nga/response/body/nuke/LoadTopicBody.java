package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.TopicInfoSimple;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * 加载用户最近主题
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/7 09:41
 **/
@Getter
@Setter
public class LoadTopicBody {
    @JsonAlias("0")
    LinkedHashMap<Integer, TopicInfoSimple> data;
}
