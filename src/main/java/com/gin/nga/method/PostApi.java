package com.gin.nga.method;

import com.gin.nga.call.NgaUploadCall;
import com.gin.nga.client.NgaClient;
import com.gin.nga.params.UploadParam;

/**
 * post.php的API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 15:54
 */
public class PostApi {

    //todo
    /**
     * 上传附件
     * @param client 客户端
     * @param attachUrl 上传接口地址
     * @param param 参数
     * @return com.gin.nga.call.NgaUploadCall
     * @author bx002
     * @since 2023/4/20 9:40
     */
    public static NgaUploadCall upload(NgaClient client, String attachUrl, UploadParam param){
        return client.callUpload(attachUrl, param);
    }
}   
