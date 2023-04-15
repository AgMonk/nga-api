package com.gin.nga.response.field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 礼物
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/15 12:35
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gift {
    Integer id;
    Integer count;
    public String getUrl() {
        return id == null ? null : String.format("https://img4.nga.178.com/ngabbs/nga_classic/items/5_%d.png", id);
    }
}
