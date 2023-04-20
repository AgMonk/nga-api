package com.gin.nga.response.body;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.Attachment;
import lombok.Getter;
import lombok.Setter;

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
    Integer thumb;
    @JsonAlias("url")
    String url;

    /**
     * 绝对路径
     * @return 绝对路径
     */
    public String getAbsoluteUrl() {
        return url == null ? null : (Attachment.PREFIX + url);
    }
}   
