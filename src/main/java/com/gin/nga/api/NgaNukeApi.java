package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.params.nuke.FavorForumParam;
import com.gin.nga.params.nuke.NukeBaseParam;
import com.gin.nga.params.nuke.UserInfoParam;
import com.gin.nga.response.body.MessageBody;
import com.gin.nga.response.body.nuke.FavorForumBody;
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



    /**
     * 查询用户信息
     * @param userId 用户id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.UserInfoBody>
     * @author bx002
     * @since 2023/4/17 11:28
     */
    public NgaJsonCall< UserInfoBody.Res> getUserInfo(long  userId){
        return nuke(new UserInfoParam(userId), UserInfoBody.Res.class);
    }
/**
     * 查询用户信息
     * @param username 用户名
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.UserInfoBody>
     * @author bx002
     * @since 2023/4/17 11:28
     */
    public NgaJsonCall< UserInfoBody.Res> getUserInfo(String  username){
        return nuke(new UserInfoParam(username),  UserInfoBody.Res.class);
    }

/**
 * 查询收藏版面
 * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.FavorForumBody.Res>
 * @author bx002
 * @since 2023/4/17 14:50
 */
    public NgaJsonCall<FavorForumBody.Res> getFavorForum(){
        final FavorForumParam param = new FavorForumParam();
        param.setAction("get");
        return nuke(param, FavorForumBody.Res.class);
    }
    /**
     * 添加收藏版面
     * @param forumId 版面id
     * @return com.gin.nga.call.NgaJsonCall<?>
     * @author bx002
     * @since 2023/4/17 15:01
     */
    public NgaJsonCall<MessageBody> addFavorForum(long forumId){
        final FavorForumParam param = new FavorForumParam();
        param.setAction("add");
        param.setForumId(forumId);
        return nuke(param,MessageBody.class);
    }

 /**
     * 删除收藏版面
     * @param forumId 版面id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.MessageBody>
     * @author bx002
     * @since 2023/4/17 15:01
     */
    public NgaJsonCall<MessageBody> delFavorForum(long forumId){
        final FavorForumParam param = new FavorForumParam();
        param.setAction("del");
        param.setForumId(forumId);
        return nuke(param, MessageBody.class);
    }
/**
     * 添加收藏合集
     * @param colTid 版面id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.MessageBody>
     * @author bx002
     * @since 2023/4/17 15:01
     */
    public NgaJsonCall<MessageBody> addFavorCol(long colTid){
        final FavorForumParam param = new FavorForumParam();
        param.setAction("add");
        param.setColTid(colTid);
        return nuke(param,MessageBody.class);
    }

 /**
     * 删除收藏合集
     * @param colTid 版面id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.MessageBody>
     * @author bx002
     * @since 2023/4/17 15:01
     */
    public NgaJsonCall<MessageBody> delFavorCol(long colTid){
        final FavorForumParam param = new FavorForumParam();
        param.setAction("del");
        param.setColTid(colTid);
        return nuke(param,MessageBody.class);
    }


    public <T> NgaJsonCall<T> nuke(NukeBaseParam param,Class<T> responseClass){
        return client.callJson(NgaPhpApi.nuke,param,null,responseClass);
    }
}   
