package com.gin.nga.bbscode.enums;

import com.gin.nga.bbscode.utils.PatternFactory;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 论坛标签名称(可识别标签名称列表)
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/29 14:14
 */
@RequiredArgsConstructor
public enum TagName {
    /**
     * 附件
     */
    attach("attach"),
    /**
     * 小标题
     */
    heading("h"),
    /**
     * 列表
     */
    list("list"),
    /**
     * 列表项目
     */
    li("li"),
    /**
     * 引用框
     */
    quote("quote"),
    /**
     * 随机块
     */
    randomblock("randomblock"),
    /**
     * 随机块外层
     */
    randomblocks("randomblocks"),
    /**
     * 视频标签
     */
    flash("flash"),
    /**
     * 折叠框
     */
    collapse("collapse"),
    /**
     * 图片
     */
    img("img"),
    /**
     * 删除线
     */
    del("del"),
    /**
     * 加粗
     */
    bold("b"),
    /**
     * 下划线
     */
    underline("u"),
    /**
     * 斜体
     */
    italic("i"),
    /**
     * 回复链接
     */
    pid("pid"),
    /**
     * 用户链接
     */
    uid("uid"),
    /**
     * 主题链接
     */
    tid("tid"),
    /**
     * 链接
     */
    url("url"),
    /**
     * 字体
     */
    font("font"),
    /**
     * 字体颜色
     */
    color("color"),
    /**
     * 表格
     */
    table("table"),
    /**
     * 字体大小
     */
    size("size"),
    /**
     * 表格行
     */
    tr("tr"),
    /**
     * 向右浮动
     */
    r("r"),
    /**
     * 向左浮动
     */
    l("l"),
    /**
     * 表格-单元格
     */
    td("td"),
    /**
     * 骰子
     */
    dice("dice"),
    /**
     * 对齐方式: left right center
     */
    align("align"),
    /**
     * 代码
     */
    code("code"),
    /**
     * 文本(不存在该标签，将文本视为被该标签包裹)
     */
    text("text"),


    ;
    public final String name;

    /**
     * 根据名称字段查找适配的标签名
     *
     * @param name 名称
     * @return 标签名
     */
    public static TagName findByName(String name) {
        return list().stream().filter(i -> i.name.equals(name)).findFirst().orElse(null);
    }

    /**
     * 返回第一个匹配的标签名枚举
     * @param code 代码
     * @return 标签名
     */
    public static TagName matcher(String code){
        return list().stream().filter(i->i.getMatchPattern().matcher(code).find()).findFirst().orElse(null);
    }

    /**
     * 返回排序好的枚举列表
     * @return 枚举列表
     */
    @NotNull
    public static List<TagName> list() {
        return Arrays.stream(values()).sorted((a, b) -> {
            final String an = a.name;
            final String bn = b.name;
            if (an.length() != bn.length()) {
                return bn.length() - an.length();
            }
            return bn.compareTo(an);
        }).toList();
    }

    /**
     * 获取用于匹配标签的正则表达式
     * @return 正则表达式
     */
    public Pattern getMatchPattern() {
        return PatternFactory.getPattern(String.format("^\\[%s([^\\[]*?)](.*?)\\[/%s]", name, name));
    }

    /**
     * 获取用于解析度正则表达式
     * @return 正则表达式
     */
    public Pattern getParsePattern() {
        return PatternFactory.getPattern(String.format("^\\[%s([^\\[]*?)](.*)\\[/%s]$", name, name));
    }


}
