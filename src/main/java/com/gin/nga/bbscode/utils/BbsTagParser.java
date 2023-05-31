package com.gin.nga.bbscode.utils;

import com.gin.nga.bbscode.entity.BbsTag;
import com.gin.nga.bbscode.enums.TagName;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 论坛标签解析
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/29 14:45
 */
public class BbsTagParser {
    /**
     * 匹配连续[randomblock]的正则
     */
    public static final Pattern RANDOMBLOCK_PATTERN = Pattern.compile("(\\[randomblock].+?\\[/randomblock])+");
    /**
     * 匹配回复模板的正则
     */
    public static final Pattern REPLY_PATTERN = Pattern.compile("(\\[b]Reply to.+?\\[/b])");
    /**
     * 匹配不规范的td标签
     */
    public static final Pattern TD_PATTERN = Pattern.compile("\\[td(\\d+)]");
    /**
     * 匹配不规范的td标签
     */
    public static final Pattern TD_PATTERN_2 = Pattern.compile("\\[td (.+?)]");
    /**
     * 匹配heading的不规范写法
     */
    public static final Pattern HEADING_PATTERN = Pattern.compile("===(.+?)===");


    /**
     * 解析论坛正文
     * @param content 论坛正文
     * @return 标签列表
     */
    public static List<BbsTag> parseContent(String content) {
        if (ObjectUtils.isEmpty(content)) {
            return new ArrayList<>();
        }
//        预处理
        content = preHandle(content);
//        解析
        return parseCode(content);
    }

    /**
     * 解析标签,将一段bbsCode解析为一个标签数组
     * @param code 标签
     * @return 标签列表
     */
    public static List<BbsTag> parseCode(String code) {
//        todo
        final ArrayList<BbsTag> res = new ArrayList<>();
//        todo 逐个字符检查是否遇到了可识别的bbsCode
        int i = 0;
        while (i < code.length()) {
            //            如果当前位置不是 [ , 继续搜索
            if (code.charAt(i) != '[') {
                i++;
                continue;
            }
            // 裁剪code
            final String prefixCode = code.substring(0, i);
            final String suffixCode = code.substring(i);
            //当前位置是 [ , 尝试匹配标签名称
            final TagName tagName = TagName.matcher(suffixCode);
            if (tagName != null) {
                //标签名匹配成功
                if (i > 0) {
                    // 如果i大于0，把前方字符串作为文本加入
                    res.add(BbsTag.text(prefixCode));
                }
                // 从后段代码中正确的位置截取代码范围
                final String subCode = getSubCode(suffixCode, tagName);
                if (subCode != null) {
                    // 匹配成功
                    // 添加本段代码到列表
                    res.add(new BbsTag(tagName,subCode));
                    // 截断原代码段
                    code = suffixCode.substring( subCode.length());
                    i = 0;
                }

            } else {
                i++;
            }
        }
        if (i>0) {
            // 循环结束，i依然大于0，表示有剩余文字，添加一个文本节点
            res.add(BbsTag.text(code));
        }


        return res;
    }

    /**
     * 从代码片段中截取指定标签名称的分段
     *
     * @param suffixCode 代码片段
     * @param tagName    标签名称
     * @return 代码分段
     */
    private static String getSubCode(String suffixCode, TagName tagName) {
        int i = 0;
        final String start = "[" + tagName.name;
        final String end = "[/" + tagName.name + "]";

        while (i < suffixCode.length()) {
            // 尝试截取的片段
            final int endIndex = suffixCode.indexOf(end, i) + end.length();
            final String s = suffixCode.substring(0, endIndex);
//            如果截取范围内标签的起止标记数量相同，认为匹配
            if (StringUtils.countMatches(s, start) == StringUtils.countMatches(s, end)) {
                return s;
            }
            i = endIndex;
        }
        return null;
    }

    /**
     * 正文的预处理
     *
     * @param content 正文
     * @return 预处理后的标签字符串
     */
    private static String preHandle(String content) {
//        替换不规范的 ===
        {
            content = HEADING_PATTERN.matcher(content).replaceAll("[h]$1[/h]");
        }

        //删除多余的空格 h list quote
        {
            content = removeBr(content, TagName.heading);
            content = removeBr(content, TagName.list);
            content = removeBr(content, TagName.quote);
            content = removeBr(content, TagName.randomblock);
        }
//        [*] 标签规范化
        {
            content = content.replace("<br/>[*]", "[*]").replace("<br>[*]", "[*]");
            content = content
                    .replace("[*]", "[/li][li]")
                    .replace("[list][/li]", "[list]")
                    .replace("[/list]", "[/li][/list]");
        }
//        [randomblock] 标签规范化
        {
            final Matcher matcher = RANDOMBLOCK_PATTERN.matcher(content);
            List<String> replacement = new ArrayList<>();
            while (matcher.find()) {
                replacement.add(matcher.group());
            }
            for (String r : replacement) {
                content = content.replace(r, String.format("[%s]%s[/%s]",TagName.randomblocks, r,TagName.randomblocks));
            }
        }

//        为预设的回复模板添加 quote
        {
            content = REPLY_PATTERN.matcher(content).replaceAll("[quote]$1[/quote]");
        }

//        td 标签规范化
        {
            content = TD_PATTERN.matcher(content).replaceAll("[td=width$1]");
            content = TD_PATTERN_2.matcher(content).replaceAll("[td=$1]");
        }


        return content;
    }

    /**
     * 对本就会渲染为一行的标签，删除其首尾多余的br换行符
     *
     * @param content 正文
     * @param tagName 标签名称
     * @return 处理后的正文
     */
    private static String removeBr(String content, TagName tagName) {
        return PatternFactory.getPattern("(<br\\/*>)+\\[(\\/*)" + tagName.name + "]")
                .matcher(content).replaceAll("[$2" + tagName.name + "]");
    }

}   
