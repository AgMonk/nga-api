package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.utils.BitUtils;
import lombok.Getter;
import lombok.Setter;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
     * 缩略图bit数据, 位置与后缀的对应关系
     */
    public static final Map<Integer,String> THUMB_MAP = new LinkedHashMap<>(){{
        put(3,".thumb_ss.jpg");
        put(4,".thumb_s.jpg");
        put(5,".thumb.jpg");
        put(6,".medium.jpg");
    }};
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
        final ArrayList<String> list = new ArrayList<>();
        final String s = BitUtils.parse(thumbBit);
        final char trueValue = '1';
        THUMB_MAP.forEach((i,suffix)->{
            if (s.length()>i && s.charAt(i)==trueValue) {
                list.add(url+suffix);
            }
        });
        return list;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = URLDecoder.decode(originalFilename, StandardCharsets.UTF_8);
    }
}
