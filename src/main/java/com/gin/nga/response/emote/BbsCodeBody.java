package com.gin.nga.response.emote;

import com.gin.nga.response.Option;

import java.util.List;

/**
 * @param emoteGroups 官方表情包
 * @param fonts       字体
 * @param fontColor   字体颜色
 * @param fontSize    字体大小
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 14:27
 */
public record BbsCodeBody(
        List<EmoteGroup> emoteGroups,
        List<Option> fonts,
        List<String> fontColor,
        List<Option> fontSize
) {
}
