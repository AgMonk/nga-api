package com.gin.nga.bbscode.entity;

import com.gin.nga.bbscode.enums.TagName;
import com.gin.nga.bbscode.utils.BbsTagParser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 论坛标签\
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/29 14:14
 */
@Getter
@Setter
@NoArgsConstructor
public class BbsTag {
    /**
     * 标签名称
     */
    TagName name;
    /**
     * 参数
     */
    String params;
    /**
     * 内部文本
     */
    String text;
    /**
     * 子节点
     */
    List<BbsTag> children;
    /**
     * 原始代码
     */
    String raw;

    /**
     * 通过代码段生成一个标签
     *
     * @param name 标签名
     * @param code 代码段
     */
    public BbsTag(TagName name, String code) {
        this.name = name;
        this.raw = code;
        final Pattern pattern = name.getParsePattern();
        final Matcher matcher = pattern.matcher(code);
        if (matcher.find()) {
            final String paramString = matcher.group(1).replace("=", "");
            params = ObjectUtils.isEmpty(paramString) ? null : paramString;
            final String innerCode = matcher.group(2);

            if (name == TagName.code) {
                //代码框 内部直接作为文本处理
                this.children = List.of(text(innerCode));
            } else {
                // 继续解析子节点
                this.children = BbsTagParser.parseCode(innerCode);
            }
        }
    }

    /**
     * 生成纯文本标签
     *
     * @param text 文本
     * @return 文本标签
     */
    public static BbsTag text(String text) {
        final BbsTag tag = new BbsTag();
        tag.setName(TagName.text);
        tag.setText(StringEscapeUtils.unescapeHtml4(text));
        return tag;
    }
}
