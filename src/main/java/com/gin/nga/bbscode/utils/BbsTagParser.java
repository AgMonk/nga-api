package com.gin.nga.bbscode.utils;

import com.gin.nga.bbscode.entity.BbsTag;
import com.gin.nga.bbscode.enums.TagName;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 论坛标签解析
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

        System.out.println(content);

        return parse(content);
    }

    /**
     * 从一段论坛代码中解析出一个标签列表
     * @param code 论坛代码
     * @return 标签列表
     */
    private static List<BbsTag> parse(String code) {
//        todo

        return null;
    }

    /**
     * 正文的预处理
     * @param content 正文
     * @return 预处理后的标签字符串
     */
    private static String preHandle(String content) {
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
                content = content.replace(r, String.format("[randomblocks]%s[/randomblocks]", r));
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
     * @param content 正文
     * @param tagName 标签名称
     * @return 处理后的正文
     */
    private static String removeBr(String content, TagName tagName) {
        return PatternFactory.getPattern("(<br\\/*>)+\\[(\\/*)" + tagName.name + "]")
                .matcher(content).replaceAll("[$2" + tagName.name + "]");
    }

}   
