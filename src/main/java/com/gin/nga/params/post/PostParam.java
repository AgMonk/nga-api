package com.gin.nga.params.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.enums.Language;
import com.gin.common.utils.UnicodeUtils;
import com.gin.jackson.serializer.BooleanJsonSerializer;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.enums.Hidden;
import com.gin.nga.response.body.UploadBody;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 发帖参数
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 11:07
 */
@Getter
public class PostParam {
    public static final Pattern AT_BBS_CODE_PATTERN = Pattern.compile("\\[@(.+?)]");

    final int step = 2;
    /**
     * 标题
     */
    @JsonProperty("post_subject")
    final String title;
    /**
     * 正文
     */
    @JsonProperty("post_content")
    final String content;
    /**
     * (仅对超过编辑时限的回复) 添加到末尾,当准备请求返回的同名字段为1时需要设置为true
     */
    @JsonProperty("modify_append")
    @JsonSerialize(using = BooleanJsonSerializer.class)
    final boolean modifyAppend;
    /**
     * 隐藏内容,版主可见
     */
    @JsonProperty("hidden")
    final Hidden hidden;
    /**
     * 匿名发帖
     */
    @JsonProperty("anony")
    @JsonSerialize(using = BooleanJsonSerializer.class)
    final boolean anony;
    /**
     * 附件列表
     */
    @JsonIgnore
    final List<UploadBody> attachs = new ArrayList<>();
    /**
     * at 用户
     */
    @JsonProperty("mention")
    String mention;

    public PostParam(String title, String content, boolean modifyAppend, Hidden hidden, boolean anony) {
        content = ObjectUtils.isEmpty(content) ? "" : content;

        this.title = title;
        this.content = encodeUnicode(content);
        this.modifyAppend = modifyAppend;
        this.hidden = hidden;
        this.anony = anony;

        // 处理 at 代码
        final Matcher matcher = AT_BBS_CODE_PATTERN.matcher(content);
        final List<String> users = new ArrayList<>();
        while (matcher.find()) {
            users.add(matcher.group(1));
        }
        if (users.size() > 0) {
            this.mention = String.join("\t", users);
        }
    }

    /**
     * 常规发帖
     *
     * @param title   标题
     * @param content 正文
     */
    public PostParam(String title, String content) {
        this(title, content, false, null, false);
    }

    /**
     * 常规发帖
     *
     * @param content 正文
     */
    public PostParam(String content) {
        this(null, content, false, null, false);
    }

    /**
     * unicode编码, 编码韩文和其他字符
     *
     * @param raw 待编码字符串
     */
    public static String encodeUnicode(String raw) {
        StringBuilder sb = new StringBuilder();
        UnicodeUtils.unicodeIterator(raw, (index, codePoint, s,unicode) -> {
            final Language lang = Language.findLanguage(codePoint);
            //4位16进制上限
            final int limit = 65535;
            if (lang == Language.ko || (lang == Language.other && codePoint > limit)) {
                sb.append(unicode);
            } else {
                sb.append(s);
            }
        });
        final String t = sb.toString();
        return t.length() < 6 ? t + "[b][/b]" : t;
    }

    public boolean add(UploadBody uploadBody) {
        return attachs.add(uploadBody);
    }

    public void add(int index, UploadBody element) {
        attachs.add(index, element);
    }

    public boolean addAll(@NotNull Collection<? extends UploadBody> c) {
        return attachs.addAll(c);
    }

    public void clear() {
        attachs.clear();
    }

    @JsonProperty("attachments")
    public String getAttachments() {
        return attachs.stream().map(UploadBody::getAttachments).collect(Collectors.joining("\t"));
    }

    @JsonProperty("attachments_check")
    public String getAttachmentsCheck() {
        return attachs.stream().map(UploadBody::getAttachmentsCheck).collect(Collectors.joining("\t"));
    }

    public UploadBody remove(int index) {
        return attachs.remove(index);
    }
}
