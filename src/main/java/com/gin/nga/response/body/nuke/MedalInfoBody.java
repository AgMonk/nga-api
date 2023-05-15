package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.Medal;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * 道具信息(徽章信息)
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/15 09:47
 */
@Getter
@Setter
public class MedalInfoBody {
    @JsonAlias("0")
    LinkedHashMap<String, Medal> data;
}   
