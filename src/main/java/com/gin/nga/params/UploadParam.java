package com.gin.nga.params;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.BoolParam;
import com.gin.nga.enums.WatermarkPosition;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 上传文件参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 16:29
 */
@Getter
@Setter
public class UploadParam {
    @JsonProperty("v2")
    final int version = 1;
    @JsonProperty("func")
    final String function = "upload";
    @JsonProperty("__inchst")
    final String charset = "UTF8";
    @JsonProperty("origin_domain")
    final String domain = "bbs.nga.cn";
    @JsonProperty("__output")
    final int output = 11;
    /**
     * 准备请求中拿到的同名字段
     */
    @JsonProperty("auth")
    final String auth;
    /**
     * 版面id
     */
    @JsonProperty("fid")
    final long forumId;
    /**
     * 描述
     */
    @JsonProperty("attachment_file1_dscp")
    final String description;
    /**
     * 未知
     */
    @JsonProperty("attachment_file1_img")
    final int attachment_file1_img = 1;
    /**
     * 是否自动转换尺寸(压缩图片,超过4M的图片如果不压缩会报错)
     */
    @JsonProperty("attachment_file1_auto_size")
    final BoolParam autoSize;
    /**
     * 水印位置
     */
    @JsonProperty("attachment_file1_watermark")
    final WatermarkPosition watermarkPosition;
    /**
     * 附件文件UTF8编码文件原名再urlencode
     */
    @JsonProperty("attachment_file1_url_utf8_name")
    final String utf8Name;
    /**
     * 文件
     */
    @JsonProperty("attachment_file1")
    @JsonIgnore
    final File file;
    public UploadParam(
            @NotNull File file,
            @NotEmpty String auth,
            long forumId
    ) {
        this(file, auth, forumId, null, null);
    }

    public UploadParam(
            @NotNull File file,
            @NotEmpty String auth,
            long forumId,
            String description,
            WatermarkPosition watermarkPosition
    ) {
        this.auth = auth;
        this.forumId = forumId;
        this.description = description;
        this.watermarkPosition = watermarkPosition;
        this.file = file;

        final int k = 1024;
        final int m = k * k;
        // 文件超过4M 自动压缩
        this.autoSize = file.length() > 4 * m ? BoolParam.yes : BoolParam.no;
        this.utf8Name = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8);
    }

}
