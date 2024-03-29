package com.gin.nga.method;


import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.call.NgaUploadCall;
import com.gin.nga.client.NgaClient;
import com.gin.nga.enums.ItemType;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.enums.ReplyStatus;
import com.gin.nga.params.UploadParam;
import com.gin.nga.params.nuke.*;
import com.gin.nga.params.nuke.base.NukeEditHistoryParam;
import com.gin.nga.params.nuke.base.UserOldNameParam;
import com.gin.nga.params.nuke.base.VoteParam;
import com.gin.nga.params.nuke.block.BlockDataListParam;
import com.gin.nga.params.nuke.block.BlockDataSetParam;
import com.gin.nga.params.nuke.favor.FavorAddParam;
import com.gin.nga.params.nuke.favor.FavorDelParam;
import com.gin.nga.params.nuke.favor.FavorFolderListParam;
import com.gin.nga.params.nuke.favor.FavorFolderModifyParam;
import com.gin.nga.params.nuke.favorforum.FavorForumEditParam;
import com.gin.nga.params.nuke.favorforum.FavorForumParam;
import com.gin.nga.params.nuke.follow.FollowGetParam;
import com.gin.nga.params.nuke.follow.FollowParam;
import com.gin.nga.params.nuke.follow.FollowStatusParam;
import com.gin.nga.params.nuke.item.*;
import com.gin.nga.params.nuke.mission.MissionCheckInParam;
import com.gin.nga.params.nuke.mission.MissionCheckParam;
import com.gin.nga.params.nuke.mission.MissionListParam;
import com.gin.nga.params.nuke.notice.NoticeClearParam;
import com.gin.nga.params.nuke.notice.NoticeEnableParam;
import com.gin.nga.params.nuke.notice.NoticeListParam;
import com.gin.nga.params.nuke.notice.NoticeStatusParam;
import com.gin.nga.params.nuke.pm.*;
import com.gin.nga.response.body.BaseMessageBody;
import com.gin.nga.response.body.FavorFolderBody;
import com.gin.nga.response.body.UserOldNameBody;
import com.gin.nga.response.body.nuke.*;
import com.gin.nga.response.field.BlockData;

import java.util.List;

/**
 * nuke.php的API
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 15:54
 */
public class NukeApi {
    /**
     * 删除附件
     *
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
     *
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
     *
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
     *
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
     *
     * @param client  客户端
     * @param forumId 父版面id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.BlockSubForumBody.Res>
     * @since 2023/4/17 15:58
     */
    public static NgaJsonCall<BlockSubForumBody> blockSubForumList(NgaClient client, long forumId) {
        return client.nuke(new BlockSubForumParam("get", forumId), BlockSubForumBody.class);
    }

    /**
     * 用户签到
     *
     * @param client 客户端
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.CheckInBody>
     * @since 2023/4/21 16:35
     */
    public static NgaJsonCall<CheckInBody> checkIn(NgaClient client) {
        return client.nuke(new CheckInParam(), CheckInBody.class);
    }

    /**
     * 查询签到状态
     *
     * @param client 客户端
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.CheckInStatusBody>
     * @since 2023/4/21 16:34
     */
    public static NgaJsonCall<CheckInStatusBody> checkInStatus(NgaClient client) {
        return client.nuke(new CheckInStatusParam(), CheckInStatusBody.class);
    }

    /**
     * 查询收藏夹列表
     *
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
     *
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
     *
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
     *
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.FavorForumBody.Res>
     * @since 2023/4/17 14:50
     */
    public static NgaJsonCall<FavorForumBody> favorForumList(NgaClient client) {
        return client.nuke(new FavorForumParam(), FavorForumBody.class);
    }

    /**
     * 添加收藏主题和回复
     *
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
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/19 12:00
     */
    public static NgaJsonCall<BaseMessageBody> favorTopicDel(NgaClient client, FavorDelParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 查询徽章信息
     *
     * @param client   客户端
     * @param subTypes 徽章id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.MedalInfoBody>
     * @since 2023/5/15 9:50
     */
    public static NgaJsonCall<MedalInfoBody> medalInfoList(NgaClient client, List<Integer> subTypes) {
        return client.nuke(new ItemInfoParam(ItemType.MEDAL, subTypes), MedalInfoBody.class);
    }

    /**
     * 检查任务
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.MissionCheckBody>
     * @since 2023/4/21 11:22
     */
    public static NgaJsonCall<MissionCheckBody> missionCheck(NgaClient client, MissionCheckParam param) {
        //todo 目前不可用
//        return client.nuke(param, MissionCheckBody.class);
        return null;
    }

    /**
     * 检查签到类任务
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.MissionCheckBody>
     * @since 2023/4/21 11:36
     */
    public static NgaJsonCall<MissionCheckInBody> missionCheckIn(NgaClient client, MissionCheckInParam param) {
        return client.nuke(param, MissionCheckInBody.class);
    }

    /**
     * 查询任务列表
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.MissionBody>
     * @since 2023/4/21 9:57
     */
    public static NgaJsonCall<MissionBody> missionList(NgaClient client, MissionListParam param) {
        return client.nuke(param, MissionBody.class);
    }

    /**
     * 清空提醒消息
     *
     * @param client 客户端
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/18 13:48
     */
    public static NgaJsonCall<BaseMessageBody> noticeClear(NgaClient client) {
        return client.nuke(new NoticeClearParam(), BaseMessageBody.class);
    }

    /**
     * 关闭 / 开启某一楼提醒消息, 关闭后回复状态中将出现 {@link  ReplyStatus} 的 NO_HINT 属性
     *
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
     *
     * @param client    客户端
     * @param timeLimit 只返回时间戳大于该值的提醒
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.NoticeBody.Res>
     * @since 2023/4/18 9:26
     */
    public static NgaJsonCall<NoticeBody> noticeList(NgaClient client, long timeLimit) {
        return client.nuke(new NoticeListParam(timeLimit), NoticeBody.class);
    }

    /**
     * 获取当前登录用户提醒信息状态(疑似无效)
     *
     * @param client 客户端
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.NoticeStatusBody>
     * @since 2023/4/21 16:58
     */
    public static NgaJsonCall<NoticeStatusBody> noticeStatus(NgaClient client) {
        return client.nuke(new NoticeStatusParam(), NoticeStatusBody.class);
    }

    /**
     * 向私信会话中添加更多的参与者
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/20 17:06
     */
    public static NgaJsonCall<BaseMessageBody> pmAdd(NgaClient client, PmAddParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 添加用户到私信黑名单
     *
     * @param client 客户端
     * @param userId 用户id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/20 17:35
     */
    public static NgaJsonCall<BaseMessageBody> pmBlockAdd(NgaClient client, long userId) {
        return client.nuke(new PmBlockAddParam(userId), BaseMessageBody.class);
    }

    /**
     * 从私信黑名单移除用户
     *
     * @param client 客户端
     * @param userId 用户id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/20 17:35
     */
    public static NgaJsonCall<BaseMessageBody> pmBlockDel(NgaClient client, long userId) {
        return client.nuke(new PmBlockDelParam(userId), BaseMessageBody.class);
    }

    /**
     * 查询私信黑名单
     *
     * @param client 客户端
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.PmBlockListBody>
     * @since 2023/4/20 17:37
     */
    public static NgaJsonCall<PmBlockListBody> pmBlockList(NgaClient client) {
        return client.nuke(new PmBlockListParam(), PmBlockListBody.class);
    }

    /**
     * 私信会话踢人
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/20 17:22
     */
    public static NgaJsonCall<BaseMessageBody> pmKick(NgaClient client, PmKickParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 查询私信列表
     *
     * @param client 客户端
     * @param page   页码
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.PrivateMessageBody>
     * @since 2023/4/20 13:45
     */
    public static NgaJsonCall<PmListBody> pmList(NgaClient client, int page) {
        return client.nuke(new PmListParam(page), PmListBody.class);
    }

    /**
     * 发送新私信
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/20 16:40
     */
    public static NgaJsonCall<BaseMessageBody> pmNew(NgaClient client, PmNewParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 查询私信内容
     *
     * @param param  参数
     * @param client 客户端
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.PrivateMessageReplyBody>
     * @since 2023/4/20 15:46
     */
    public static NgaJsonCall<PmReplyBody> pmRead(NgaClient client, PmReadParam param) {
        return client.nuke(param, PmReplyBody.class);
    }

    /**
     * 回复私信
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/20 16:56
     */
    public static NgaJsonCall<BaseMessageBody> pmReply(NgaClient client, PmReplyParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 查询版面权限信息
     *
     * @param client  客户端
     * @param forumId 版面ID
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @author bx002
     * @since 2023/5/26 9:49
     */
    public static NgaJsonCall<BaseMessageBody> privilegeList(NgaClient client, long forumId) {
        return client.nuke(new PrivilegeListParam(forumId), BaseMessageBody.class);
    }

    /**
     * 举报一个回复
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/5/8 9:13
     */
    public static NgaJsonCall<BaseMessageBody> report(NgaClient client, ReportParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 点赞或点踩
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.RecommendBody>
     * @since 2023/4/19 14:31
     */
    public static NgaJsonCall<TopicRecommendBody> topicRecommend(NgaClient client, TopicRecommendParam param) {
        return client.nuke(param, TopicRecommendBody.class);
    }

    /**
     * 设置签名
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @since 2023/4/26 16:13
     */
    public static NgaJsonCall<BaseMessageBody> signatureSet(NgaClient client, SignatureSetParam param) {
        return client.callJson(NgaPhpApi.nuke, null, param, BaseMessageBody.class);
    }

    /**
     * 查询版面的主题分类
     *
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
     *
     * @param client 客户端
     * @param userId 用户id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.UserInfoBody>
     * @since 2023/4/17 11:28
     */
    public static NgaJsonCall<UserInfoBody> userInfoGet(NgaClient client, long userId) {
        return client.nuke(new UserInfoParam(userId), UserInfoBody.class);
    }

    /**
     * 查询用户使用过的用户名
     * @param client 客户端
     * @param userId 用户id
     * @return 用户名
     */
    public static NgaJsonCall<UserOldNameBody> userOldNameGet(NgaClient client, long userId) {
        return client.nuke(new UserOldNameParam(userId), UserOldNameBody.class);
    }

    /**
     * 查询用户信息
     *
     * @param client   客户端
     * @param username 用户名
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.UserInfoBody>
     * @since 2023/4/17 11:28
     */
    public static NgaJsonCall<UserInfoBody> userInfoGet(NgaClient client, String username) {
        return client.nuke(new UserInfoParam(username), UserInfoBody.class);
    }

    /**
     * 投票/投注
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @author bx002
     * @since 2023/6/1 13:59
     */
    public static NgaJsonCall<BaseMessageBody> vote(NgaClient client, VoteParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 查询屏蔽的用户和内容
     *
     * @param client 客户端
     * @return com.gin.nga.call.NgaJsonCall<java.lang.String>
     * @author bx002
     * @since 2023/6/8 10:52
     */
    public static NgaJsonCall<BlockDataBody> blockDataList(NgaClient client) {
        return client.nuke(new BlockDataListParam(client.getUserId()), BlockDataBody.class);
    }

    /**
     * 设置屏蔽的用户和内容
     *
     * @param client 客户端
     * @return com.gin.nga.call.NgaJsonCall<java.lang.String>
     * @author bx002
     * @since 2023/6/8 10:52
     */
    public static NgaJsonCall<BaseMessageBody> blockDataSet(NgaClient client, BlockData data) {
        return client.nuke(new BlockDataSetParam(data), BaseMessageBody.class);
    }

    /**
     * 查询商店里的道具列表
     *
     * @param client 客户端
     * @param param  参数
     * @author bx002
     * @since 2023/6/23 10:04
     */
    public static NgaJsonCall<ItemBody> itemStore(NgaClient client, ItemStoreParam param) {
        return client.nuke(param, ItemBody.class);
    }

    /**
     * 查询自己的道具列表
     *
     * @param client 客户端
     * @param page   页码
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.ItemBody>
     * @author bx002
     * @since 2023/6/23 11:34
     */
    public static NgaJsonCall<ItemBody> itemInventory(NgaClient client, int page) {
        return client.nuke(new ItemInventoryParam(page), ItemBody.class);
    }

    /**
     * 购买道具
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @author bx002
     * @since 2023/6/23 14:56
     */

    public static NgaJsonCall<BaseMessageBody> itemBuy(NgaClient client, ItemBuyParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 使用道具
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @author bx002
     * @since 2023/6/23 15:04
     */
    public static NgaJsonCall<BaseMessageBody> itemUse(NgaClient client, ItemUseParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 加载用户最近主题
     *
     * @param client 客户端
     * @param userId 用户id
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.LoadTopicBody>
     * @author bx002
     * @since 2023/8/7 9:48
     */
    public static NgaJsonCall<LoadTopicBody> loadRecentTopic(NgaClient client, long userId) {
        return client.nuke(new LoadRecentTopicParam(userId), LoadTopicBody.class);
    }

    /**
     * 关注
     *
     * @param client 客户端
     * @param param  参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @author bx002
     * @since 2023/8/7 10:05
     */
    public static NgaJsonCall<BaseMessageBody> follow(NgaClient client, FollowParam param) {
        return client.nuke(param, BaseMessageBody.class);
    }

    /**
     * 查询回复的编辑历史(编辑时间与发布时间间隔5分钟以上时才会被记录)
     *
     * @param client 客户端
     * @param param  参数
     * @return call
     */
    public static NgaJsonCall<EditHistoryBody> editHistory(NgaClient client, NukeEditHistoryParam param) {
        return client.nuke(param, EditHistoryBody.class);
    }


    /**
     * 查询关注列表
     *
     * @param client 客户端
     * @param page   页码
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.FollowUserListBody>
     * @author bx002
     * @since 2023/8/7 10:13
     */

    public static NgaJsonCall<FollowUserListBody> followGet(NgaClient client, int page) {
        return client.nuke(new FollowGetParam(page), FollowUserListBody.class);
    }

    /**
     * 查询关注动态
     *
     * @param client 客户端
     * @param page   页码
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.FollowStatusBody>
     * @author bx002
     * @since 2023/8/7 10:41
     */
    public static NgaJsonCall<FollowStatusBody> followStatus(NgaClient client, int page) {
        return client.nuke(new FollowStatusParam(page), FollowStatusBody.class);
    }
}
