package com.gin.nga.response.field;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.ReputationUserDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * read.php接口中各用户的声望数据
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/28 14:33
 */
@Getter
@Setter
@JsonDeserialize(using = ReputationUserDeserializer.class)
public class ReputationUser {
    /**
     * 声望id
     */
    Integer id;
    /**
     * 声望名称
     */
    String name;
    /**
     * 声望值：uid->声望值
     */
    Map<Long,Integer> data = new HashMap<>();


}   
