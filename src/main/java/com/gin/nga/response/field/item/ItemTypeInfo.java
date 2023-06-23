package com.gin.nga.response.field.item;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gin.jackson.utils.JacksonUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * 道具类型信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 10:39
 */
@Getter
@Setter
public class ItemTypeInfo {
    public static final String URL_PREFIX = "https://img4.nga.178.com/ngabbs/nga_classic/items/";

    @JsonAlias("name")
    String name;
    @JsonAlias("icon")
    String icon;
    @JsonAlias("dscp")
    String description;
    /**
     * 购买限制
     */
    @JsonAlias("permission_buy_store")
    String permissionBuyStore;

    /*buff 相关字段*/

    @JsonAlias("buff_id")
    Integer buffId;
    @JsonAlias("buff_value_0")
    Integer buffValue0;
    /**
     * buff持续时间
     */
    @JsonAlias("buff_last")
    Integer buffLast;

    public String getUrl(){
        return URL_PREFIX+icon;
    }

    public static LinkedHashMap<String, ItemTypeInfo> parse(Object obj) throws JsonProcessingException {
        final String s = JacksonUtils.MAPPER.writeValueAsString(obj);
        return JacksonUtils.MAPPER.readValue(s, new TypeReference<LinkedHashMap<String, ItemTypeInfo>>() {
        });
    }
}   
