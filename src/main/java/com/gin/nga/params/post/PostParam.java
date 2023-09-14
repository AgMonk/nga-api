package com.gin.nga.params.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.enums.Language;
import com.gin.common.utils.UnicodeUtils;
import com.gin.jackson.serializer.BooleanJsonSerializer;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.deserializer.BooleanDeserializer;
import com.gin.nga.enums.Hidden;
import com.gin.nga.response.body.UploadBody;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
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
@Setter
@NoArgsConstructor
public class PostParam {
    //
    private static final int OPT_VOTE_REPLY_VIEW = 2048;
    //
    private static final int OPT_VOTE_END_VIEW = 4096;
    //同步关注动态(仅在回复时
    private static final int OPT_REPLY_FOLLOW_PUSH = 8192;
    /**
     * 单贴回复参数
     */
    public static final int REPLY_ONCE = 1073741824;
    public static final Pattern AT_BBS_CODE_PATTERN = Pattern.compile("\\[@(.+?)]");
    /**
     * 需要使用unicode编码的语言
     */
    public static final List<Language> UNICODE_LANGUAGE = new ArrayList<>(Arrays.asList(Language.ko));
    /**
     * 不需要编码的语言
     */
    public static final List<Language> NOT_UNICODE_LANGUAGE = new ArrayList<>(Arrays.asList(Language.CJK_symbols_and_punctuation));

    final int step = 2;
    /**
     * 标题
     */
    @JsonProperty("post_subject")
    String title;
    /**
     * 正文
     */
    @JsonProperty("post_content")
    String content;
    /**
     * (仅对超过编辑时限的回复) 添加到末尾,当准备请求返回的同名字段为1时需要设置为true
     */
    @JsonProperty("modify_append")
    @JsonSerialize(using = BooleanJsonSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    boolean modifyAppend;
    /**
     * 隐藏内容,版主可见
     */
    @JsonProperty("hidden")
    Hidden hidden;
    /**
     * 匿名发帖
     */
    @JsonProperty("anony")
    @JsonSerialize(using = BooleanJsonSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    boolean anony;
    /**
     * 附件列表
     */
    @JsonIgnore
    List<UploadBody> attachs = new ArrayList<>();
    /**
     * at 用户
     */
    @JsonProperty("mention")
    String mention;

    @JsonProperty("tpic_misc_bit1")
    Integer topicMiscBit;
    /**
     * 发帖选项 <a href="https://img4.nga.178.com/common_res/js_postfunc_v2.js">参考</a>
     */
    @JsonProperty("post_opt")
    Integer postOpt;

    /**
     * 发帖参数
     * @param title 标题
     * @param content 正文
     * @param modifyAppend 是否添加到末尾
     * @param hidden 隐藏选项
     * @param anony 是否匿名
     * @param replyOnce 是否发布为单回复贴
     * @param replyFollowPush 是否同步到动态
     */
    public PostParam(String title, String content, boolean modifyAppend, Hidden hidden, boolean anony, boolean replyOnce, boolean replyFollowPush) {
        content = ObjectUtils.isEmpty(content) ? "" : content;

        this.title = title;
        this.content = encodeUnicode(content);
        this.modifyAppend = modifyAppend;
        this.hidden = hidden;
        this.anony = anony;

        //单帖回复(发布新主题时使用)
        if (replyOnce) {
            topicMiscBit = REPLY_ONCE;
        }

        if (replyFollowPush) {
            postOpt = postOpt != null ? postOpt : 0;
            postOpt = postOpt | OPT_REPLY_FOLLOW_PUSH;
        }

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
        this(title, content, false, null, false, false,false);
    }

    /**
     * 常规发帖
     *
     * @param content 正文
     */
    public PostParam(String content) {
        this(null, content, false, null, false, false,false);
    }

    /**
     * unicode编码, 编码韩文和其他字符
     *
     * @param raw 待编码字符串
     */
    public static String encodeUnicode(String raw) {
        StringBuilder sb = new StringBuilder();
        UnicodeUtils.unicodeIterator(raw, (index, codePoint, s, unicode) -> {
            final Language lang = Language.findLanguage(codePoint);
            //4位16进制上限
            if (useUnicode(lang, codePoint)) {
                sb.append(unicode);
            } else {
                sb.append(s);
            }
        });
        final String t = sb.toString();
        return t.length() < 6 ? t + "[b][/b]" : t;
    }

    /**
     * 返回是否使用unicode
     * @param lang 语言
     * @param codePoint codePoint
     * @return 是否使用unicode
     */
    public static boolean useUnicode(Language lang, int codePoint) {
        if (UNICODE_LANGUAGE.contains(lang)) {
            return true;
        }
        if (NOT_UNICODE_LANGUAGE.contains(lang)) {
            return false;
        }
        if (lang == Language.other) {
            if (codePoint < 256) {
                return false;
            }
            if (codePoint > 65535) {
                return true;
            }
            return true;
        }
        return false;
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
