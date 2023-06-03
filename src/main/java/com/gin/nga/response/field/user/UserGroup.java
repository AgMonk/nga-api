package com.gin.nga.response.field.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * 用户组
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 16:19
 */
@Setter
@Getter
@NoArgsConstructor
public class UserGroup {
    Integer id;
    String name;
    /**
     * 未知数据 todo
     */
    Long data;

    public UserGroup(Map<Integer,Serializable> map) {
        this.name = String.valueOf(map.get(0));
        this.data = Long.valueOf(String.valueOf(map.get(1)));
        this.id = Integer.valueOf(String.valueOf(map.get(2)));
    }
}
