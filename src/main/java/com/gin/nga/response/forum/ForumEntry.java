package com.gin.nga.response.forum;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * 首页版面入口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 11:15
 */
@Getter
@Setter
public class ForumEntry {
    /**
     * 版面id
     */
    @JsonAlias("fid")
    Long forumId;
    /**
     * 入口名称
     */
    @JsonAlias("name")
    String name;
    /**
     * 入口名称(简称)
     */
    @JsonAlias("nameS")
    String simpleName;
    /**
     * 功能备注
     */
    @JsonAlias("info")
    String info;
    /**
     * 功能备注(简称)
     */
    @JsonAlias("infoS")
    String simpleInfo;
    /**
     * 功能备注(完整)
     */
    @JsonAlias("infoL")
    String longInfo;
    /**
     * 位数据
     */
    @JsonAlias("bit")
    Integer bit;
    /**
     * 合集id
     */
    @JsonAlias("stid")
    Long colTid;
}   
