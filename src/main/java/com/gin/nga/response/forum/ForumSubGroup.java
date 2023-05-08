package com.gin.nga.response.forum;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * 首页版面子组
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 11:14
 */
@Getter
@Setter
public class ForumSubGroup {
    @JsonAlias("name")
    String name;
    /**
     * 版面入口
     */
    @JsonAlias("content")
    LinkedHashMap<Integer,ForumEntry> forums;

    @JsonIgnore
    public boolean isEmpty(){
       return forums==null || forums.size()==0;
    }
}   
