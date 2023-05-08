package com.gin.nga.response.emotion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 表情图标组
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 14:18
 */
@Getter
@Setter
@NoArgsConstructor
public class EmoteGroup {
    public static final String NAME_KEY = "_______name";
    /**
     * 组key
     */
    String key;
    /**
     * 组名
     */
    String name;
    /**
     * 表情图标
     */
    List<Emote> emotions = new ArrayList<>();

    public EmoteGroup(String key, Map<String, String> map) {
        this.key = key;
        map.forEach((k, v) -> {
            if (NAME_KEY.equals(k)) {
                this.name = v;
            } else {
                this.emotions.add(new Emote(key, k, v));
            }
        });

    }
}
