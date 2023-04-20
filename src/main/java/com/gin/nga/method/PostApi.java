package com.gin.nga.method;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.client.NgaClient;
import com.gin.nga.params.post.PostParam;
import com.gin.nga.params.post.PrepareParam;
import com.gin.nga.response.post.PostBody;
import com.gin.nga.response.post.PrepareBody;

/**
 * post.php的API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 15:54
 */
public class PostApi {
    /**
     * 发帖准备
     * @param client 客户端
     * @param prepareParam 准备参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.post.PostPrepareBody>
     * @author bx002
     * @since 2023/4/20 10:20
     */
    public static NgaJsonCall<PrepareBody> postPrepare(NgaClient client, PrepareParam prepareParam){
        return client.post(prepareParam, null, PrepareBody.class);
    }
    /**
     * 正式发帖
     * @param client 客户端
     * @param prepareParam 准备参数
     * @param postParam 发帖参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.post.PostBody>
     * @author bx002
     * @since 2023/4/20 12:22
     */
    public static NgaJsonCall<PostBody> postSend(NgaClient client, PrepareParam prepareParam, PostParam postParam){
        return client.post(prepareParam,postParam,PostBody.class);
    }

}   
