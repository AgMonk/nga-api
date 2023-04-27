package com.gin.nga.response.alterlog;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.common.utils.StrUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 最后编辑记录
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 17:55
 */
@Getter
@Setter
@NoArgsConstructor
public class LastEdit  {
    /**
     * 修改时间
     */
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime timestamp;
    /**
     * 操作人uid
     */
    Long userId;
    /**
     * 操作人用户名
     */
    String username;

    /**
     * 解析字符串构造, 用例:E1680766452 38148967 星光下的彩虹
     * @param split 字符串
     */
    public LastEdit(String[] split) {
        this.timestamp = ZonedDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(split[0])), ZoneId.systemDefault());
        //noinspection AlibabaUndefineMagicConstant
        if (StrUtils.isNumber(split[1]) && !"0".equals(split[1])) {
            this.userId = Long.parseLong(split[1]);
            this.username = split[2];
        }
    }
}
