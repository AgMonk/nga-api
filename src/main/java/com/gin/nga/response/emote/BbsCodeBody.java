package com.gin.nga.response.emote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    public static final Pattern FONT_COLOR_PATTERN = Pattern.compile("'(.+?)':0");
    public static final Pattern FONT_PATTERN = Pattern.compile("'(.+?)':'(.+?)'");


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

    public BbsCodeBody(String res) {
        final String[] data = res.split("\n");

        // 获取字体数据
        this. fonts = parseData(data,
                "ubbcode.fonts = {",
                "}",
                FONT_PATTERN,
                (matcher) -> new Option(matcher.group(2), matcher.group(1)),
                (lines) -> lines);
        // 获取字体颜色数据
        this.fontColor = parseData(data,
                "ubbcode.fontColor = {",
                "}",
                FONT_COLOR_PATTERN,
                (matcher) -> matcher.group(1),
                (lines) -> lines);
        // 获取字体大小数据
        this.fontSize = parseData(data,
                "ubbcode.fontSize = {",
                "}",
                FONT_PATTERN,
                (matcher) -> new Option(matcher.group(2), matcher.group(1)),
                (lines) -> lines);

        try {
            this.emoteGroups = parseEmotes(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析官方表情包
     * @param data 原始数据按 \n切分之后的数组
     * @return 表情包分组
     */
    @NotNull
    private static List<EmoteGroup> parseEmotes(String[] data) throws JsonProcessingException {
        boolean enable = false;
        // 逐行处理
        StringBuilder builder = new StringBuilder();
        for (String line : data) {
            if ("}//s".equals(line)) {
                // 结束
                builder.append("}");
                break;
            }
            if ("ubbcode.smiles = {".equals(line)) {
                // 开始
                enable = true;
                builder.append("{");
                continue;
            }
            if (enable) {
                if (line.startsWith("//")) {
                    // 注释行，直接跳过
                    continue;
                }
                final String s = line
                        .replaceAll("//.+", "")
                        .replaceAll("^(\\w+?):", "\"$1\":")
                        .replace("'", "\"");
                if (!ObjectUtils.isEmpty(s)) {
                    builder.append(s);
                }
            }

        }

        LinkedHashMap<String, LinkedHashMap<String, String>> map = NgaRes.MAPPER.readValue(builder.toString()
                , new TypeReference<LinkedHashMap<String, LinkedHashMap<String, String>>>() {
        });
        // 0组已废弃 无法渲染
        map.remove("0");

        return map.entrySet().stream()
                .map(entry -> new EmoteGroup(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * 从 js_bbscode_core.js 文件中解析数据
     * @param data        原始数据按 \n切分之后的数组
     * @param prefix      该段数据开始的标识
     * @param suffix      该段数据结束的标识
     * @param pattern     匹配数据的正则表达式
     * @param handleLine  处理匹配上的单行数据的方法
     * @param handleLines 将所有行数据转换为指定输出数据的方法
     */
    private static <T, R> List<R> parseData(
            String[] data,
            String prefix,
            String suffix,
            Pattern pattern,
            Function<Matcher, T> handleLine,
            Function<List<T>, List<R>> handleLines
    ) {
        // 是否已找到需要的数据段
        boolean enable = false;
        List<T> lines = new ArrayList<>();
        for (String line : data) {
            // 开始记录
            if (prefix.equals(line)) {
                enable = true;
                continue;
            }
            // 结束记录
            if (enable && suffix.equals(line)) {
                break;
            }
            // 注释跳过
            if (line.startsWith("//")) {
                continue;
            }
            // 记录数据
            if (enable) {
                final Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    lines.add(handleLine.apply(matcher));
                }
            }
        }
        return handleLines.apply(lines);
    }
}
