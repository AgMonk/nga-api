package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.enums.FavorAction;
import com.gin.nga.enums.FavorType;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.params.nuke.BlockSubForumParam;
import com.gin.nga.params.nuke.FavorForumParam;
import com.gin.nga.params.nuke.NukeBaseParam;
import com.gin.nga.params.nuke.UserInfoParam;
import com.gin.nga.response.body.MessageBody;
import com.gin.nga.response.body.nuke.BlockSubForumBody;
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
     * 修改收藏
     * @param type   收藏类型
     * @param action 操作类型
     * @param id     id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.MessageBody>
     * @since 2023/4/17 15:29
     */
    public NgaJsonCall<MessageBody> editFavorForum(FavorAction action, FavorType type, long id) {
        final FavorForumParam param = new FavorForumParam();
        param.setAction(action);
        if (type == FavorType.forum) {
            param.setForumId(id);
        } else if (type == FavorType.col) {
            param.setColTid(id);
        } else {
            throw new RuntimeException("非法的类型");
        }
        return nuke(param, MessageBody.class);
    }

    /**
     * 查询子版面屏蔽情况
     * @param forumId 父版面id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.BlockSubForumBody.Res>
     * @since 2023/4/17 15:58
     */
    public NgaJsonCall<BlockSubForumBody.Res> getBlockSubForum(long forumId) {
        final BlockSubForumParam param = new BlockSubForumParam();
        param.setForumId(forumId);
        param.setAct("get");
        return nuke(param, BlockSubForumBody.Res.class);
    }

    /**
     * 查询收藏版面
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.FavorForumBody.Res>
     * @since 2023/4/17 14:50
     */
    public NgaJsonCall<FavorForumBody.Res> getFavorForum() {
        final FavorForumParam param = new FavorForumParam();
        param.setAction(FavorAction.get);
        return nuke(param, FavorForumBody.Res.class);
    }

    /**
     * 查询用户信息
     * @param username 用户名
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.UserInfoBody>
     * @since 2023/4/17 11:28
     */
    public NgaJsonCall<UserInfoBody.Res> getUserInfo(String username) {
        return nuke(new UserInfoParam(username), UserInfoBody.Res.class);
    }

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.UserInfoBody>
     * @since 2023/4/17 11:28
     */
    public NgaJsonCall<UserInfoBody.Res> getUserInfo(long userId) {
        return nuke(new UserInfoParam(userId), UserInfoBody.Res.class);
    }

    public <T> NgaJsonCall<T> nuke(NukeBaseParam param, Class<T> responseClass) {
        return client.callJson(NgaPhpApi.nuke, param, null, responseClass);
    }
}   
