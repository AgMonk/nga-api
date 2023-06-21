package com.gin.nga.response.field;

import com.gin.jackson.utils.ObjectUtils;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

/**
 * read.php接口返回的声望数据
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 14:20
 */
@NoArgsConstructor
public class ReputationMap extends LinkedHashMap<Integer, Integer> {
    public ReputationMap(String s) {
        super();
        if (ObjectUtils.isEmpty(s)) {
            return ;
        }
        for (String pair : s.split(",")) {
            final String[] split = pair.split("_");
            put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
    }
}
