package com.gin.nga.response.emote;

import com.gin.nga.response.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 14:27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BbsCodeBody {
    /**
     * 官方表情包
     */
    List<EmoteGroup> emoteGroups;
    /**
     * 字体
     */
    List<Option> fonts;
    /**
     * 字体颜色
     */
    List<String> fontColor;
    /**
     * 字体大小
     */
    List<Option> fontSize;
}
