package com.gin.nga.params.nuke.pm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.jackson.utils.ObjectUtils;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 发起新私信参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 16:25
 */
@Getter
public class PmNewParam extends PmBaseParam {
    /**
     * 标题
     */
    @JsonProperty("subject")
    final String title;
    /**
     * 正文
     */
    @JsonProperty("content")
    final String content;
    @JsonIgnore
    final List<Serializable> user;

    /**
     * 构造函数
     * @param title 标题
     * @param content 正文
     * @param user 邀请的用户id或用户名
     */
    public PmNewParam(String title, String content, List<Serializable> user) {
        super("new");
        this.title = title;
        this.content = content;
        this.user = user;
    }
    /**
     * 构造函数
     * @param title 标题
     * @param content 正文
     * @param user 邀请的用户id或用户名
     */
    public PmNewParam(String title, String content, Serializable... user) {
        this(title, content, Arrays.asList(user));
    }

    public String getTo(){
        if (ObjectUtils.isEmpty(user)) {
            throw new RuntimeException("user不允许为空");
        }
        return user.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
