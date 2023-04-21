package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.PmListBodyDeserializer;
import com.gin.nga.response.field.pm.PrivateMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 私信body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 13:43
 */
@Getter
@Setter
@JsonDeserialize(using = PmListBodyDeserializer.class)
public class PmListBody {
    /**
     * 私信
     */
    List<PrivateMessage> data;
    /**
     * 是否还有下一页
     */
    boolean hasNext;
    /**
     * 当前页
     */
    int page;
    /**
     * 每页数量
     */
    int size;

}
