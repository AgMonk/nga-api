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
    int id;
    String filename;
    String url;
    @Setter
    String suffix;

    public AvatarBuff(int id, String filename) {
        this.id = id;
        this.filename = filename;
        this.url = "https://img4.nga.178.com/ngabbs/face/"+  filename;
    }
}
