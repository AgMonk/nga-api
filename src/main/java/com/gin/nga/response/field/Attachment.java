package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.utils.BitUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * 附件信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 11:56
 */
@Getter
@Setter
public class Attachment {
    /**
     * 绝对路径的前缀
     */
    public static final String PREFIX = "https://img.nga.178.com/attachments/";
    /**
     * 图片相对地址
     */
    @JsonAlias(value = {"url", "attachurl"})
    String url;
    /**
     * 描述
     */
    @JsonAlias("dscp")
    String description;
    /**
     * 扩展名/后缀名
     */
    @JsonAlias("ext")
    String extension;

    @JsonAlias("name")
    String filename;
    @JsonAlias("path")
    String path;
    /**
     * 附件大小，单位：KB
     */
    @JsonAlias("size")
    Long size;

    @JsonAlias("type")
    String type;
    @JsonAlias("url_utf8_org_name")
    String originalFilename;
    /**
     * 缩略图信息
     */
    @JsonAlias("thumb")
    Integer thumbBit;

    /**
     * 绝对路径
     * @return 绝对路径
     */
    public String getAbsoluteUrl() {
        return url == null ? null : (PREFIX + url);
    }

    /**
     * 缩略图地址
     * @return 缩略图地址
     */
    public List<String> getThumbUrls() {
        if (thumbBit == null) {
            return null;
        }
        return BitUtils.getThumbUrls(url,thumbBit);
    }

    public void setOriginalFilename(String originalFilename) {
        try {
            this.originalFilename = URLDecoder.decode(originalFilename, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
