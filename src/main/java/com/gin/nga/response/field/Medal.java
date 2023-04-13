package com.gin.nga.response.field;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * 徽章
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 16:45
 */
@Getter
@Setter
public class Medal {
    /**
     * 图片地址前缀 , 拼接文件名即为地址
     */
    public static final String URL_PREFIX = "https://img4.nga.178.com/ngabbs/medal/";
    /**
     * 文件名
     */
    String filename;
    /**
     * 徽章名称
     */
    String name;
    /**
     * 鼠标提示
     */
    String tooltip;
    /**
     * id
     */
    Integer id;

    public Medal(Map<Integer, Serializable> data) {
        this.filename = String.valueOf(data.get(0));
        this.name = String.valueOf(data.get(1));
        this.tooltip = String.valueOf(data.get(2));
        this.id = Integer.valueOf(String.valueOf(data.get(2)));
    }

    public String getUrl() {
        return filename == null ? null : (URL_PREFIX + filename);
    }
}
