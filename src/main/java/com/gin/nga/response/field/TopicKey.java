package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 主题分类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 14:06
 */
@Getter
@Setter
@NoArgsConstructor
public class TopicKey {
    /**
     * 类型名称
     */
    String name;
    /**
     * 是否由版主设置
     */
    boolean setByModerator;

    @JsonCreator
    public TopicKey(List<Serializable> list) {
        this.name = String.valueOf(list.get(0));
        this.setByModerator = list.get(1).equals(1);
    }

    @JsonCreator
    public TopicKey(LinkedHashMap<Integer,Serializable> list) {
        this.name = String.valueOf(list.get(0));
        this.setByModerator = list.get(1).equals(1);
    }



}
