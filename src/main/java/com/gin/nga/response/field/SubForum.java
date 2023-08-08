package com.gin.nga.response.field;

import com.gin.nga.enums.SubForumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * 子版面(可能为版面或合集)
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/14 14:21
 */
@Getter
@Setter
@NoArgsConstructor
public class SubForum {
    /**
     * 类型
     */
    SubForumType type;
    /**
     * id
     */
    Long id;
    /**
     * 在 <a href="https://bbs.nga.cn/thread.php?fid=635">635版</a> 的镜像贴id
     */
    Long mirrorId;
    /**
     * 未知数据
     */
    Long data;
    /**
     * 名称
     */
    String name;

    public SubForum(String key,Map<Integer, Serializable> map) {
        this.type = key.startsWith("t")?SubForumType.COL : SubForumType.FORUM;
        this.id = getLong(map,0);
        this.name = String.valueOf(map.get(1));
        this.mirrorId = getLong(map,3);
        this.data = getLong(map,4);
    }

    private static Long getLong (Map<Integer, Serializable> map,int index){
        final Serializable v = map.get(index);
        if (v!=null) {
            return Long.parseLong(String.valueOf(v));
        }
        return null;
    }
}
