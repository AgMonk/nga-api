package com.gin.nga.response.body;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.Attachment;
import com.gin.nga.utils.BitUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 上传成功的响应
 *
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


    /*报错字段*/

    @JsonAlias("error_code")
    Integer errorCode;
    @JsonAlias("error")
    String error;

    /**
     * 缩略图地址
     *
     * @return 缩略图地址
     */
    public List<String> getThumbUrls() {
        if (thumbBit == null) {
            return null;
        }
        return BitUtils.getThumbUrls(url, thumbBit);
    }

    public String getExtension() {
        return url == null ? null : url.substring(url.lastIndexOf(".")+1);
    }

    public String getBbsCode(){
        final String extension = getExtension();
        if (extension==null) {
            return null;
        }
        switch (extension){
            case "zip":
               return String.format("[url=https://img.nga.178.com/attachments/%s]下载文件[/url]",url);
            case "mp3":
            case "mp4":
                return String.format("[flash]./%s[/flash]",url);
            default:
               return String.format("[img]./%s[/img]",url);
        }
    }

    /**
     * 绝对路径
     *
     * @return 绝对路径
     */
    public String getAbsoluteUrl() {
        return url == null ? null : (Attachment.PREFIX + url);
    }
}   
