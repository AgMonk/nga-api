package com.gin.nga.bbscode.utils;

import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.bbscode.entity.BbsTag;
import com.gin.nga.bbscode.enums.TagName;
import org.apache.commons.lang3.StringUtils;

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
    public static final Pattern HEADING_PATTERN = Pattern.compile("===(.*?)===");
    /**
     * @ 标签规范化
     */
    public static final Pattern AT_PATTERN = Pattern.compile("\\[@(.+?)]");


    /**
     * 解析论坛正文
     *
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
     *
     * @param code 标签
     * @return 标签列表
     */
    public static List<BbsTag> parseCode(String code) {
     //        todo
        final ArrayList<BbsTag> res = new ArrayList<>();
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
                    res.add(new BbsTag(tagName, subCode));
                    // 截断原代码段
                    code = suffixCode.substring(subCode.length());
                    i = 0;
                }else{
                    System.err.println("[nga-api] bbsCode 匹配失败 = " + suffixCode);
                    i++;
                }
            } else {
                i++;
            }
        }
        if (i > 0) {
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
            // 如果截取范围内标签的起止标记数量相同，匹配成功
            if (StringUtils.countMatches(s, start) == StringUtils.countMatches(s, end)) {
                return s;
            }
            // 记录上次匹配失败的位置
            if (i<endIndex){
                i = endIndex;
            }else{
                return null;
            }
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
            content = content.
                    // 把所有 [*] 替换为 [/li][li] ,包括前面带/不带换行符的
                    replaceAll("(<br/*>)*\\[\\*]", "[/li][li]")
                    // 在 [/list] 前添加 [/li]
                    .replace("[/list]", "[/li][/ul]")
                    // 在 [list] 后添加 [li]
                    .replace("[list]", "[ul][li]")
                    // 删除多余的空 li
                    .replace("[li][/li]", "");
        }
//        [randomblock] 标签规范化
        {
            final Matcher matcher = RANDOMBLOCK_PATTERN.matcher(content);
            List<String> replacement = new ArrayList<>();
            while (matcher.find()) {
                replacement.add(matcher.group());
            }
            for (String r : replacement) {
                content = content.replace(r, String.format("[%s]%s[/%s]", TagName.randomblocks.name, r, TagName.randomblocks.name));
            }
        }

//        为预设的回复模板添加 quote
        {
            content = REPLY_PATTERN.matcher(content).replaceAll("[quote]$1[/quote]");
        }

        //[u][b][i][r][l][h]规范化
        {
            content = content.replaceAll("\\[(/*)u]", String.format("[$1%s]",TagName.underline.name));
            content = content.replaceAll("\\[(/*)b]", String.format("[$1%s]",TagName.bold.name));
            content = content.replaceAll("\\[(/*)i]", String.format("[$1%s]",TagName.italic.name));
            content = content.replaceAll("\\[(/*)r]", String.format("[$1%s]",TagName.right.name));
            content = content.replaceAll("\\[(/*)l]", String.format("[$1%s]",TagName.left.name));
            content = content.replaceAll("\\[(/*)h]", String.format("[$1%s]",TagName.heading.name));
        }

//        td 标签规范化
        {
            content = TD_PATTERN.matcher(content).replaceAll("[td=width$1]");
            content = TD_PATTERN_2.matcher(content).replaceAll("[td=$1]");
        }
//        @标签规范化
        {
            content = AT_PATTERN.matcher(content).replaceAll(String.format("[%s=$1]$1[/%s]",TagName.at.name,TagName.at.name));
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
