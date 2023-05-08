package com.gin.nga.method;

import com.gin.nga.call.NgaResourceCall;
import com.gin.nga.client.NgaClient;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.forum.ForumGroupBody;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 静态资源API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 10:10
 */
public class ResourceApi {
    private static final OkHttpClient CLIENT = NgaClient.getOkHttpClient();
    /**
     * 首页版面信息地址
     */
    private static final String INDEX_FORUMS = "https://img4.nga.178.com/proxy/cache_attach/bbs_index_data.js";
    /**
     * 首页版面入口
     * @return com.gin.nga.call.NgaResourceCall<com.gin.nga.response.forum.ForumGroupRes>
     * @author bx002
     * @since 2023/5/8 11:48
     */
    public static NgaResourceCall<ForumGroupBody> indexForums(){
        final Request request = new Request.Builder().url(INDEX_FORUMS).build();
        final Call call = CLIENT.newCall(request);
        return  new NgaResourceCall<>(call, ForumGroupBody.class, (content, valueType) -> {
            return NgaRes.MAPPER.readValue(content.replace("window.script_muti_get_var_store=", ""), valueType);
        });
    }
}   
