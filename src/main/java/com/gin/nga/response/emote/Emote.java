package com.gin.nga.response.emote;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 表情图标
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 14:15
 */
@Getter
@Setter
@NoArgsConstructor
public class Emote {
    /**
     * 组内名称
     */
    String key;
    /**
     * 文件名
     */
    String filename;
    /**
     * 在正文中使用的标签
     */
    String bbsCode;
    /**
     * 图片地址
     */
    String url;

    public Emote(String groupKey, String key, String value) {
        this.key = key;
        this.bbsCode = String.format("[s:%s:%s]", groupKey, key);
        this.filename = value;
        this.url = "https://img4.nga.178.com/ngabbs/post/smile/" + value;
    }
}
