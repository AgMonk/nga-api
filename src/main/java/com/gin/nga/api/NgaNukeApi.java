package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.params.nuke.NukeBaseParam;
import com.gin.nga.response.body.nuke.UserInfoBody;
import lombok.RequiredArgsConstructor;

/**
 * 综合操作相关API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:21
 */
@RequiredArgsConstructor
public class NgaNukeApi {
    private final NgaClient client;

    //todo



    public NgaJsonCall<UserInfoBody> getUserInfo(Long uid, String username){
        return null;
    }


    public <T> NgaJsonCall<T> nuke(NukeBaseParam param,Class<T> responseClass){
        return client.callJson(NgaPhpApi.nuke,param,null,responseClass);
    }
}   
