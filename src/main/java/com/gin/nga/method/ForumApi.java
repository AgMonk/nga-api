package com.gin.nga.method;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.client.NgaClient;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.params.forum.ForumParam;
import com.gin.nga.response.body.ForumBody;

/**
 * forum.php的API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 15:52
 */
public class ForumApi {

    /**
     * 搜索版面
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ForumBody>
     * @since 2023/4/13 10:26
     */
    public static NgaJsonCall<ForumBody> search(NgaClient client, ForumParam param) {
        return client.callJson(NgaPhpApi.forum, param, null, ForumBody.class);
    }
}
