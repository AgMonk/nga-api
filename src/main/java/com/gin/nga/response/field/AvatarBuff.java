package com.gin.nga.response.field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 头像buff
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/28 14:39
 */
@Getter
@Setter
@NoArgsConstructor
public class AvatarBuff {
    /**
     * 头像图片前缀
     */
    public static final String PREFIX = "https://img4.nga.178.com/ngabbs/face/";
    int id;
    String filename;
    String url;
    String suffix;

    public String getUrl(){
        return PREFIX+filename;
    }
}
