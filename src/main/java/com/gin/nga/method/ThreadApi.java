package com.gin.nga.method;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.client.NgaClient;
import com.gin.nga.params.thread.*;
import com.gin.nga.response.body.ThreadBody;

/**
 * thread.php的API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 15:51
 */
public class ThreadApi {
    /**
     * 浏览合集主题
     * * @param client 客户端
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ThreadBody>
     * @since 2023/4/12 9:34
     */
    public static NgaJsonCall<ThreadBody> colList(NgaClient client, ColListParam param) {
        return client.thread(param);
    }

    /**
     * 搜索合集主题
     * * @param client 客户端
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ThreadBody>
     * @since 2023/4/12 9:35
     */
    public static NgaJsonCall<ThreadBody> colSearch(NgaClient client, ColSearchParam param) {
        return client.thread(param);
    }

    /**
     * 获取收藏主题/回复
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ThreadBody>
     * @since 2023/4/12 9:38
     */
    public static NgaJsonCall<ThreadBody> favor(NgaClient client, FavorParam param) {
        return client.thread(param);
    }
    /**
     * 浏览版面主题
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ThreadBody>
     * @since 2023/4/12 9:39
     */
    public static NgaJsonCall<ThreadBody> forumList(NgaClient client, ForumListParam param) {
        return client.thread(param);
    }

    /**
     * 搜索版面主题
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ThreadBody>
     * @since 2023/4/12 9:39
     */
    public static NgaJsonCall<ThreadBody> forumSearch(NgaClient client, ForumSearchParam param) {
        return client.thread(param);
    }
    /**
     * 搜索用户主题/回复
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ThreadBody>
     * @since 2023/4/12 9:39
     */
    public static NgaJsonCall<ThreadBody> userSearch(NgaClient client, UserSearchParam param) {
        return client.thread(param);
    }

}   
