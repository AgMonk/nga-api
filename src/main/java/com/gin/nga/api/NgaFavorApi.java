package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.params.nuke.ListFavorFolderParam;
import com.gin.nga.response.body.FavorFolderBody;
import lombok.RequiredArgsConstructor;

/**
 * 收藏夹相关API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 16:03
 */
@RequiredArgsConstructor
public class NgaFavorApi {
    private final NgaClient client;

    public NgaJsonCall<FavorFolderBody> get(ListFavorFolderParam param) {
        return client.nuke(param, FavorFolderBody.class);
    }
}   
