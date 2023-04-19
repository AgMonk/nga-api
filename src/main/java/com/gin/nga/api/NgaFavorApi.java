package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.params.nuke.favor.FavorAddParam;
import com.gin.nga.params.nuke.favor.FavorDelParam;
import com.gin.nga.params.nuke.favor.FavorFolderListParam;
import com.gin.nga.params.nuke.favor.FavorFolderModifyParam;
import com.gin.nga.response.body.BaseMessageBody;
import com.gin.nga.response.body.FavorFolderBody;
import jakarta.validation.constraints.NotNull;
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

    /**
     * 查询自己的收藏夹列表
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.FavorFolderBody>
     * @since 2023/4/18 16:45
     */
    public NgaJsonCall<FavorFolderBody> get() {
        return get(null, null);
    }
    /**
     * 查询收藏夹列表
     * @param page   页码
     * @param userId 用户id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.FavorFolderBody>
     * @since 2023/4/18 16:45
     */
    public NgaJsonCall<FavorFolderBody> get(Integer page, Long userId) {
        final FavorFolderListParam param = new FavorFolderListParam();
        param.setPage(page);
        param.setUserId(userId);
        return client.nuke(param, FavorFolderBody.class);
    }
    /**
     * 修改收藏夹
     * @param name     名称
     * @param isPublic 是否公开收藏
     * @param folderId 收藏夹id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/18 16:48
     */
    public NgaJsonCall<BaseMessageBody> modify(@NotNull String name, boolean isPublic, long folderId) {
        final FavorFolderModifyParam param = new FavorFolderModifyParam();
        param.setName(name);
        param.setIsPublic(isPublic ? 1 : 0);
        param.setFolderId(folderId);
        return client.nuke(param, BaseMessageBody.class);
    }
    /**
     * 添加收藏
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @author bx002
     * @since 2023/4/19 11:24
     */
    public NgaJsonCall<BaseMessageBody> add(FavorAddParam param){
        return client.nuke(param, BaseMessageBody.class)    ;
    }
    /**
     * 删除收藏
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @author bx002
     * @since 2023/4/19 12:00
     */
    public NgaJsonCall<BaseMessageBody> del(FavorDelParam param){
        return client.nuke(param, BaseMessageBody.class)    ;

    }
}   
