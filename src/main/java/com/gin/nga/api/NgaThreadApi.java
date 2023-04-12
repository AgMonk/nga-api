package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.params.PageParam;
import com.gin.nga.params.thread.*;
import com.gin.nga.response.body.ThreadBody;
import lombok.RequiredArgsConstructor;

/**
 * 主题列表API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:21
 */
@RequiredArgsConstructor
public class NgaThreadApi {
    private final NgaClient client;

    /**
     *  浏览合集主题
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ThreadBody>
     * @author bx002
     * @since 2023/4/12 9:34
     */
    public NgaJsonCall<ThreadBody> col(ColParam param) {
        return thread(param);
    }

    /**
     * 搜索合集主题
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ThreadBody>
     * @author bx002
     * @since 2023/4/12 9:35
     */
    public NgaJsonCall<ThreadBody> colSearch(ColSearchParam param) {
        return thread(param);
    }

    /**
     * 获取收藏主题/回复
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ThreadBody>
     * @author bx002
     * @since 2023/4/12 9:38
     */
    public NgaJsonCall<ThreadBody> favor(FavorParam param) {
        return thread(param);
    }

    /**
     * 浏览版面主题
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ThreadBody>
     * @author bx002
     * @since 2023/4/12 9:39
     */
    public NgaJsonCall<ThreadBody> forum(ForumParam param) {
        return thread(param);
    }

    /**
     * 搜索版面主题
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ThreadBody>
     * @author bx002
     * @since 2023/4/12 9:39
     */
    public NgaJsonCall<ThreadBody> forumSearch(ForumSearchParam param) {
        return thread(param);
    }

    /**
     * 搜索用户主题/回复
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ThreadBody>
     * @author bx002
     * @since 2023/4/12 9:39
     */
    public NgaJsonCall<ThreadBody> userSearch(UserSearchParam param) {
        return thread(param);
    }


    /**
     * 查询主题列表
     * @param queryParam 查询参数
     * @return Call
     */
    private NgaJsonCall<ThreadBody> thread(PageParam queryParam) {
        return client.callJson(NgaPhpApi.thread, queryParam, null, ThreadBody.class);
    }
}   
