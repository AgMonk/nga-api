package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.SimpleUserInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * 私信黑名单body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 17:33
 */
@Getter
@Setter
public class PmBlockListBody {
    @JsonAlias("0")
    LinkedHashMap<Integer, SimpleUserInfo> data;
}   
