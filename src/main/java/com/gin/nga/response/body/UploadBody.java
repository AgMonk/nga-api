package com.gin.nga.response.body;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.Attachment;
import com.gin.nga.utils.BitUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 上传成功的响应
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 16:15
 */
@Getter
@Setter
public class UploadBody {
    @JsonAlias("attachments")
    String attachments;
    @JsonAlias("attachments_check")
    String attachmentsCheck;
    @JsonAlias("isImg")
    Boolean isImg;
    @JsonAlias("thumb")
    Integer thumbBit;
    @JsonAlias("url")
    String url;

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

    /**
     * 绝对路径
     * @return 绝对路径
     */
    public String getAbsoluteUrl() {
        return url == null ? null : (Attachment.PREFIX + url);
    }
}   
