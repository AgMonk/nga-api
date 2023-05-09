package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * 版面搜索结果
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:15
 */
@Getter
@Setter
public class ForumInfo {
    public static final String ICON_FORMAT = "https://img4.nga.178.com/proxy/cache_attach/ficon/%du.png";
    public static final String ICON_FORMAT_COL = "https://img4.nga.178.com/proxy/cache_attach/ficon/%dv.png";

    /**
     * 描述
     */
    @JsonProperty("descrip")
    String description;
    @JsonProperty("index")
    String index;
    /**
     * 版面id
     */
    @JsonProperty("fid")
    Long forumId;
    /**
     * 名称
     */
    @JsonProperty("name")
    String name;
    /**
     * 父版面信息
     */
    @JsonProperty("parent")
    Parent parent;
    /**
     * 相关性
     */
    @JsonProperty("relevance")
    Double relevance;
    /**
     * 合集id
     */
    @JsonProperty("stid")
    Long colTid;
    /**
     * 额外信息
     */
    @JsonProperty("topic_misc_var")
    Map<Long, Serializable> topicMiscVar;
    @JsonProperty("url")
    String url;

    public String getIconUrl(){
        return this.forumId==null?null:String.format(ICON_FORMAT,forumId);
    }

    @Getter
    @Setter
    public static class Parent{
        /**
         * 描述
         */
        @JsonProperty("descrip")
        String description;
        /**
         * 版面id
         */
        @JsonProperty("fid")
        Long forumId;
        /**
         * 名称
         */
        @JsonProperty("name")
        String name;
    }

}   
