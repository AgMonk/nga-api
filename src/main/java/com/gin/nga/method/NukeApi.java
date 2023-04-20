package com.gin.nga.method;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.call.NgaUploadCall;
import com.gin.nga.client.NgaClient;
import com.gin.nga.enums.ReplyStatus;
import com.gin.nga.params.UploadParam;
import com.gin.nga.params.nuke.*;
import com.gin.nga.params.nuke.favor.FavorAddParam;
import com.gin.nga.params.nuke.favor.FavorDelParam;
import com.gin.nga.params.nuke.favor.FavorFolderListParam;
import com.gin.nga.params.nuke.favor.FavorFolderModifyParam;
import com.gin.nga.params.nuke.favorforum.FavorForumEditParam;
import com.gin.nga.params.nuke.favorforum.FavorForumParam;
import com.gin.nga.params.nuke.notice.NoticeClearParam;
import com.gin.nga.params.nuke.notice.NoticeEnableParam;
import com.gin.nga.params.nuke.notice.NoticeParam;
import com.gin.nga.params.nuke.pm.PrivateMessageListParam;
import com.gin.nga.params.nuke.pm.PrivateMessageNewParam;
import com.gin.nga.params.nuke.pm.PrivateMessageReadParam;
import com.gin.nga.response.body.BaseMessageBody;
import com.gin.nga.response.body.FavorFolderBody;
import com.gin.nga.response.body.nuke.*;

/**
 * nuke.php的API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 15:54
 */
public class NukeApi {

    //todo

    /**
     * 删除附件
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/19 15:09
     */
    public static NgaJsonCall<BaseMessageBody> attachDel(NgaClient client, DelAttachParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 上传附件
     * @param client    客户端
     * @param attachUrl 上传接口地址
     * @param param     参数
     * @return com.gin.nga.call.NgaUploadCall
     * @since 2023/4/20 9:40
     */
    public static NgaUploadCall attachUpload(NgaClient client, String attachUrl, UploadParam param) {
        return client.callUpload(attachUrl, param);
    }

    /**
     * 添加子版面屏蔽
     * @param client  客户端
     * @param forumId 父版面id
     * @param id      填写 {@link com.gin.nga.response.field.SubForum} 的 mirrorId 字段，或主题列表中的 {@link  com.gin.nga.response.field.TopicInfo} 的 topicId 字段
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.MessageBody>
     * @since 2023/4/17 16:26
     */
    public static NgaJsonCall<BaseMessageBody> blockSubForumAdd(NgaClient client, long forumId, long id) {
        final BlockSubForumParam param = new BlockSubForumParam("set", forumId);
        param.setAddId(id);
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 移除子版面屏蔽
     * @param client  客户端
     * @param forumId 父版面id
     * @param id      填写 {@link com.gin.nga.response.field.SubForum} 的 mirrorId 字段，或主题列表中的 {@link  com.gin.nga.response.field.TopicInfo} 的 topicId 字段
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.MessageBody>
     * @since 2023/4/17 16:26
     */
    public static NgaJsonCall<BaseMessageBody> blockSubForumDel(NgaClient client, long forumId, long id) {
        final BlockSubForumParam param = new BlockSubForumParam("set", forumId);
        param.setDelId(id);
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 查询子版面屏蔽情况
     * @param client  客户端
     * @param forumId 父版面id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.BlockSubForumBody.Res>
     * @since 2023/4/17 15:58
     */
    public static NgaJsonCall<BlockSubForumBody> blockSubForumList(NgaClient client, long forumId) {
        return client.nuke(new BlockSubForumParam("get", forumId), BlockSubForumBody.class);
    }

    /**
     * 查询收藏夹列表
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.FavorFolderBody>
     * @since 2023/4/19 12:27
     */
    public static NgaJsonCall<FavorFolderBody> favorFolderList(NgaClient client, FavorFolderListParam param) {
        return client.nuke(param, FavorFolderBody.class);
    }

    /**
     * 修改收藏夹
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/18 16:48
     */
    public static NgaJsonCall<BaseMessageBody> favorFolderModify(NgaClient client, FavorFolderModifyParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 添加和删除收藏版面
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/19 15:45
     */
    public static NgaJsonCall<BaseMessageBody> favorForumEdit(NgaClient client, FavorForumEditParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 查询收藏版面
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.FavorForumBody.Res>
     * @since 2023/4/17 14:50
     */
    public static NgaJsonCall<FavorForumBody> favorForumList(NgaClient client) {
        return client.nuke(new FavorForumParam(), FavorForumBody.class);
    }

    /**
     * 添加收藏主题和回复
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/19 11:24
     */
    public static NgaJsonCall<BaseMessageBody> favorTopicAdd(NgaClient client, FavorAddParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 删除收藏的主题和回复
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/19 12:00
     */
    public static NgaJsonCall<BaseMessageBody> favorTopicDel(NgaClient client, FavorDelParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 清空提醒消息
     * @param client 客户端
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/18 13:48
     */
    public static NgaJsonCall<BaseMessageBody> noticeClear(NgaClient client) {
        return client.nuke(new NoticeClearParam(), BaseMessageBody.class);
    }

    /**
     * 关闭 / 开启某一楼提醒消息, 关闭后回复状态中将出现 {@link  ReplyStatus} 的 NO_HINT 属性
     * @param param  参数
     * @param client 客户端
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/19 14:17
     */
    public static NgaJsonCall<BaseMessageBody> noticeEnable(NgaClient client, NoticeEnableParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 查询提醒消息
     * @param client 客户端
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.NoticeBody.Res>
     * @since 2023/4/18 9:26
     */
    public static NgaJsonCall<NoticeBody> noticeList(NgaClient client) {
        return client.nuke(new NoticeParam(), NoticeBody.class);
    }

    /**
     * 点赞或点踩
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.RecommendBody>
     * @since 2023/4/19 14:31
     */
    public static NgaJsonCall<ScoreBody> score(NgaClient client, ScoreParam param) {
        return client.nuke(param, ScoreBody.class);
    }

    /**
     * 查询版面的主题分类
     * @param client  客户端
     * @param forumId 版面id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.TopicKeys>
     * @since 2023/4/18 14:46
     */
    public static NgaJsonCall<TopicKeys> topicKeyList(NgaClient client, long forumId) {
        return client.nuke(new TopicKeyParam(forumId), TopicKeys.class);
    }

    /**
     * 查询用户信息
     * @param client 客户端
     * @param userId 用户id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.UserInfoBody>
     * @since 2023/4/17 11:28
     */
    public static NgaJsonCall<UserInfoBody> userInfoGet(NgaClient client, long userId) {
        return client.nuke(new UserInfoParam(userId), UserInfoBody.class);
    }

    /**
     * 查询用户信息
     * @param client   客户端
     * @param username 用户名
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.UserInfoBody>
     * @since 2023/4/17 11:28
     */
    public static NgaJsonCall<UserInfoBody> userInfoGet(NgaClient client, String username) {
        return client.nuke(new UserInfoParam(username), UserInfoBody.class);
    }
    /**
     * 查询私信列表
     * @param client   客户端
     * @param page 页码
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.PrivateMessageBody>
     * @author bx002
     * @since 2023/4/20 13:45
     */
    public static NgaJsonCall<PrivateMessageListBody> privateMessageList(NgaClient client, int page){
        return  client.nuke(new PrivateMessageListParam(page), PrivateMessageListBody.class);
    }
    /**
     * 查询私信内容
     * @param client 客户端
     * @param messageId 消息id
     * @param page 页码
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.PrivateMessageReplyBody>
     * @author bx002
     * @since 2023/4/20 15:46
     */
    public static NgaJsonCall<PrivateMessageReplyBody> privateMessageRead(NgaClient client, long messageId, int page){
        return client.nuke(new PrivateMessageReadParam(messageId, page), PrivateMessageReplyBody.class);
    }
    /**
     * 发送新短消息
     * @param client 客户端
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @author bx002
     * @since 2023/4/20 16:40
     */
    public static NgaJsonCall<BaseMessageBody> privateMessageNew(NgaClient client, PrivateMessageNewParam param){
        return client.nuke(param,BaseMessageBody.class);
    }
}   
