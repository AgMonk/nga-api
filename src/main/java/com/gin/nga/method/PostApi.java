package com.gin.nga.method;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.client.NgaClient;
import com.gin.nga.params.post.PrepareParam;
import com.gin.nga.response.post.PostPrepareBody;

/**
 * post.php的API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 15:54
 */
public class PostApi {

    //todo

    /**
     * 回复准备
     * @param client 客户端
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.post.PostPrepareBody>
     * @author bx002
     * @since 2023/4/20 10:20
     */
    public static NgaJsonCall<PostPrepareBody> postPrepare(NgaClient client, PrepareParam param){
        return client.post(param, null, PostPrepareBody.class);
    }

}   
