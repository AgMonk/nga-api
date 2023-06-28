package com.gin.nga.response;

import com.gin.nga.response.field.AvatarBuff;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UI相关数据
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/28 14:33
 */
@Getter
public class CommonUiData {
    public static final Pattern COMMON_UI_PATTERN = Pattern.compile("v0==(\\d+) \\? '(.+?)'");

    final List<AvatarBuff> avatarBuffs = new ArrayList<>();

    public CommonUiData(String res) {
        final Matcher matcher = COMMON_UI_PATTERN.matcher(res);
        final HashMap<Integer, AvatarBuff> map = new HashMap<>();
        while (matcher.find()) {
            final int id = Integer.parseInt(matcher.group(1));
            final String value = matcher.group(2);
            if (value.endsWith(".png")) {
                final AvatarBuff buff = new AvatarBuff(id, value);
                avatarBuffs.add(buff);
                map.put(id, buff);
            } else if (map.containsKey(id)) {
                map.get(id).setSuffix(String.format("...%s~",value));
            }
        }
    }
}
