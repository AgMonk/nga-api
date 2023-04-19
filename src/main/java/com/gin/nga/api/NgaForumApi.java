package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.params.forum.ForumParam;
import com.gin.nga.params.nuke.BlockSubForumParam;
import com.gin.nga.params.nuke.favorforum.FavorForumEditParam;
import com.gin.nga.params.nuke.favorforum.FavorForumParam;
import com.gin.nga.response.body.BaseMessageBody;
import com.gin.nga.response.body.ForumBody;
import com.gin.nga.response.body.nuke.BlockSubForumBody;
import com.gin.nga.response.body.nuke.FavorForumBody;
import lombok.RequiredArgsConstructor;

/**
 * 版面相关API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:21
 */
@RequiredArgsConstructor
public class NgaForumApi {
    private final NgaClient client;

    /**
     * 添加子版面屏蔽
     * @param forumId 父版面id
     * @param id      填写 {@link com.gin.nga.response.field.SubForum} 的 mirrorId 字段，或主题列表中的 {@link  com.gin.nga.response.field.TopicInfo} 的 topicId 字段
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.MessageBody>
     * @since 2023/4/17 16:26
     */
    public NgaJsonCall<BaseMessageBody> addBlockSubForum(long forumId, long id) {
        final BlockSubForumParam param = new BlockSubForumParam("set", forumId);
        param.setAddId(id);
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 移除子版面屏蔽
     * @param forumId 父版面id
     * @param id      填写 {@link com.gin.nga.response.field.SubForum} 的 mirrorId 字段，或主题列表中的 {@link  com.gin.nga.response.field.TopicInfo} 的 topicId 字段
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.MessageBody>
     * @since 2023/4/17 16:26
     */
    public NgaJsonCall<BaseMessageBody> delBlockSubForum(long forumId, long id) {
        final BlockSubForumParam param = new BlockSubForumParam("set", forumId);
        param.setDelId(id);
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 修改收藏
     * @param type   收藏类型
     * @param action 操作类型
     * @param id     id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.MessageBody>
     * @since 2023/4/17 15:29
     */
    public NgaJsonCall<BaseMessageBody> editFavorForum(FavorForumEditParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 查询子版面屏蔽情况
     * @param forumId 父版面id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.BlockSubForumBody.Res>
     * @since 2023/4/17 15:58
     */
    public NgaJsonCall<BlockSubForumBody> getBlockSubForum(long forumId) {
        return client.nuke(new BlockSubForumParam("get", forumId), BlockSubForumBody.class);
    }

    /**
     * 查询收藏版面
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.FavorForumBody.Res>
     * @since 2023/4/17 14:50
     */
    public NgaJsonCall<FavorForumBody> getFavorForum() {
        return client.nuke(new FavorForumParam(), FavorForumBody.class);
    }

    /**
     * 搜索版面
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ForumBody>
     * @since 2023/4/13 10:26
     */
    public NgaJsonCall<ForumBody> search(ForumParam param) {
        return client.callJson(NgaPhpApi.forum, param, null, ForumBody.class);
    }

}
