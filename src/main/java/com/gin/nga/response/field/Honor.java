package com.gin.nga.response.field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.common.utils.TimeUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * 荣誉称号
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 10:47
 */
@Getter
@Setter
@NoArgsConstructor
public class Honor {
    /**
     * 获得时间
     */
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime timestamp;
    /**
     * 备注
     */
    String remark;
    /**
     * 名称
     */
    String name;

    public Honor(String s) {
        if (ObjectUtils.isEmpty(s)) {
            return;
        }

        s = s.trim();
        final String blank = " ";
        if (!s.contains(blank)) {
            this.name = s;
            return;
        }
        final String[] split = s.split(blank);

        long time = Long.parseLong(split[0]);
        this.timestamp = ZonedDateTime.ofInstant(Instant.ofEpochSecond(time), TimeUtils.CHINESE_ZONE_ID);
        this.remark = split[1];
        this.name = split.length > 2 ? split[2] : null;
    }
}
