package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.response.body.ThreadBody;
import com.gin.nga.utils.ParamUtils;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

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
     * 浏览合集主题
     * @return 主题列表
     */
    public NgaJsonCall<ThreadBody> col() {
        //todo
        return null;
    }

    /**
     * 搜索合集主题
     * @return 主题列表
     */
    public NgaJsonCall<ThreadBody> colSearch() {
        //todo
        return null;
    }

    /**
     * 获取收藏主题/回复
     * @param page     页数
     * @param folderId 收藏夹id
     * @return 主题/回复列表
     */
    public NgaJsonCall<ThreadBody> favor(int page, long folderId) {
        final HashMap<String, Serializable> queryParam = ParamUtils.pageParam(page);
        queryParam.put("favor", folderId);
        return thread(queryParam, ThreadBody.class);
    }

    /**
     * 浏览版面主题
     * @return 主题列表
     */
    public NgaJsonCall<ThreadBody> forum() {
        //todo
        return null;
    }

    /**
     * 搜索版面主题
     * @return 主题列表
     */
    public NgaJsonCall<ThreadBody> forumSearch() {
        //todo
        return null;
    }

    /**
     * 搜索用户主题/回复
     * @return 主题列表
     */
    public NgaJsonCall<ThreadBody> userSearch() {
        //todo
        return null;
    }


    /**
     * 查询主题列表
     * @param queryParam    查询参数
     * @param responseClass 响应类型
     * @return Call
     */
    private <T> NgaJsonCall<T> thread(Object queryParam, Class<T> responseClass) {
        return client.callJson(NgaPhpApi.thread, queryParam, null, responseClass);
    }
}   
