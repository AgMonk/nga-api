package com.gin.nga.response.forum;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * 首页版面分组
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 11:12
 */
@Getter
@Setter
public class ForumGroup {
    @JsonAlias("id")
    String id;
    @JsonAlias("name")
    String name;
    /**
     * 子组
     */
    @JsonAlias("content")
    LinkedHashMap<Integer, ForumSubGroup> subGroups;
@JsonIgnore
    public boolean isEmpty() {
        return subGroups == null || subGroups.size() == 0;
    }

    /**
     * 清理空的子组
     */
    public void clear(){
        if (subGroups!=null){
            subGroups.entrySet().removeIf(entry -> entry.getValue().isEmpty());
        }
    }
}   
