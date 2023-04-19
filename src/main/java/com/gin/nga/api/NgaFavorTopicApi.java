package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.params.nuke.favor.FavorAddParam;
import com.gin.nga.params.nuke.favor.FavorDelParam;
import com.gin.nga.params.nuke.favor.FavorFolderListParam;
import com.gin.nga.params.nuke.favor.FavorFolderModifyParam;
import com.gin.nga.response.body.BaseMessageBody;
import com.gin.nga.response.body.FavorFolderBody;
import lombok.RequiredArgsConstructor;

/**
 * 收藏夹相关API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 16:03
 */
@RequiredArgsConstructor
public class NgaFavorTopicApi {
    private final NgaClient client;

   /**
    * 查询收藏夹列表
    * @param param 参数
    * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.FavorFolderBody>
    * @author bx002
    * @since 2023/4/19 12:27
    */
    public NgaJsonCall<FavorFolderBody> list(FavorFolderListParam param) {
        return client.nuke(param, FavorFolderBody.class);
    }
    /**
     * 修改收藏夹
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/18 16:48
     */
    public NgaJsonCall<BaseMessageBody> modify(FavorFolderModifyParam param) {
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
