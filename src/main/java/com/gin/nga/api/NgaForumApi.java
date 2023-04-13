package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.params.forum.ForumParam;
import com.gin.nga.response.body.ForumBody;
import lombok.RequiredArgsConstructor;

/**
 * 版面相关API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:21
 */
@RequiredArgsConstructor
public class NgaForumApi {
    private final NgaClient client;

    /**
     * 搜索版面
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ForumBody>
     * @author bx002
     * @since 2023/4/13 10:26
     */
    public NgaJsonCall<ForumBody> search(ForumParam param){
        return client.callJson(NgaPhpApi.forum, param, null, ForumBody.class);
    }
}   
