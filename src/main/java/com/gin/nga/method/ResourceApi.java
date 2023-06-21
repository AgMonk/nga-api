package com.gin.nga.method;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.call.NgaResourceCall;
import com.gin.nga.client.NgaClient;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.Option;
import com.gin.nga.response.emote.BbsCodeBody;
import com.gin.nga.response.emote.EmoteGroup;
import com.gin.nga.response.forum.ForumGroupBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 静态资源API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 10:10
 */
public class ResourceApi {
    public static final Pattern FONT_COLOR_PATTERN = Pattern.compile("'(.+?)':0");
    public static final Pattern FONT_PATTERN = Pattern.compile("'(.+?)':'(.+?)'");
    private static final OkHttpClient CLIENT = NgaClient.getOkHttpClient();
    /**
     * 首页版面信息地址
     */
    private static final String INDEX_FORUMS = "https://img4.nga.178.com/proxy/cache_attach/bbs_index_data.js";
    private static final String CORE = "https://img4.nga.178.com/common_res/js_bbscode_core.js";

    /**
     * 官方表情包、字体、字体大小、字体颜色
     * @return com.gin.nga.call.NgaResourceCall<com.gin.nga.response.emotion.EmoteBody>
     * @since 2023/5/8 14:37
     */
    public static NgaResourceCall<BbsCodeBody> bbsCode() {
        final Request request = new Request.Builder().url(CORE).build();
        return new NgaResourceCall<>(CLIENT.newCall(request), BbsCodeBody.class, (res, clazz) -> {
            final String[] data = res.split("\n");

            // 获取字体数据
            List<Option> fonts = parseData(data,
                                           "ubbcode.fonts = {",
                                           "}",
                                           FONT_PATTERN,
                                           (matcher) -> new Option(matcher.group(2), matcher.group(1)),
                                           (lines) -> lines);
            // 获取字体颜色数据
            List<String> fontColor = parseData(data,
                                               "ubbcode.fontColor = {",
                                               "}",
                                               FONT_COLOR_PATTERN,
                                               (matcher) -> matcher.group(1),
                                               (lines) -> lines);
            // 获取字体大小数据
            List<Option> fontSize = parseData(data,
                                           "ubbcode.fontSize = {",
                                           "}",
                                              FONT_PATTERN,
                                           (matcher) -> new Option(matcher.group(2), matcher.group(1)),
                                           (lines) -> lines);

            final List<EmoteGroup> emoteGroups = parseEmotes(data);
            return new BbsCodeBody(emoteGroups, fonts, fontColor, fontSize);
        });
    }

    /**
     * 首页版面入口
     * @return com.gin.nga.call.NgaResourceCall<com.gin.nga.response.forum.ForumGroupRes>
     * @since 2023/5/8 11:48
     */
    public static NgaResourceCall<ForumGroupBody> indexForums() {
        final Request request = new Request.Builder().url(INDEX_FORUMS).build();
        return new NgaResourceCall<>(CLIENT.newCall(request), ForumGroupBody.class, (content, valueType) -> {
            return NgaRes.MAPPER.readValue(content.replace("window.script_muti_get_var_store=", ""), valueType);
        });
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

        LinkedHashMap<String, LinkedHashMap<String, String>> map = NgaRes.MAPPER.readValue(builder.toString(), new TypeReference<LinkedHashMap<String, LinkedHashMap<String, String>>>() {
        });
        // 0组已废弃 无法渲染
        map.remove("0");

        final List<EmoteGroup> emoteGroups = map.entrySet().stream()
                .map(entry -> new EmoteGroup(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return emoteGroups;
    }
}
