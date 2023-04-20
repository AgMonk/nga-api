package com.gin.nga.params.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.BooleanJsonSerializer;
import com.gin.nga.response.body.UploadBody;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 发帖参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 11:07
 */
@Getter
@RequiredArgsConstructor
public class PostParam {
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
     * 隐藏内容,版主可见
     */
    @JsonProperty("hidden")
    @JsonSerialize(using = BooleanJsonSerializer.class)
    final Boolean hidden;
    /**
     * 匿名发帖
     */
    @JsonProperty("anony")
    @JsonSerialize(using = BooleanJsonSerializer.class)
    final Boolean anony;
    /**
     * 附件列表
     */
    @JsonIgnore
    final List<UploadBody> attachs = new ArrayList<>();

    /**
     * 常规发帖
     * @param title 标题
     * @param content 正文
     */
    public PostParam(String title, String content) {
        this(title, content, null,null);
    }
    /**
     * 常规发帖
     * @param content 正文
     */
    public PostParam(String content) {
        this(null, content, null,null);
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