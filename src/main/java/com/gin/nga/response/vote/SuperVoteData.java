package com.gin.nga.response.vote;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.common.utils.TimeUtils;
import com.gin.nga.enums.VoteResult;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;

/**
 * 投票数据的父类
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/1 10:31
 */
@Getter
@Setter
public abstract class SuperVoteData {
    /**
     * 最多选择 x 项
     */
    Integer maxSelect;
    /**
     * 声望限制
     */
    Integer reputation;
    /**
     * 结束时间
     */
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime end;
    /**
     * 结果展示方式
     */
    VoteResult result;
    /**
     * 尚未解析的数据，留给子类
     */
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    /**
     * 总人数
     */
    int totalCount = 0;

    public SuperVoteData(String s) {
//            解析vote字段
        final String[] array = s.split("~");
        for (int i = 0; i < array.length-1; i+=2) {
            final String key = array[i];
            final String value = array[i + 1];

            if ("max_select".equals(key)) {
                this.maxSelect = Integer.parseInt(value);
            }else if ("priv".equals(key)){
                this.reputation = Integer.parseInt(value.split("_")[1]);
            }else if ("end".equals(key)){
                this.end = ZonedDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(value)), TimeUtils.CHINESE_ZONE_ID);
            }else if ("opt".equals(key)){
                this.result = VoteResult.findById(Integer.parseInt(value));
            }else {
                map.put(key,value);
            }
        }
    }
}
