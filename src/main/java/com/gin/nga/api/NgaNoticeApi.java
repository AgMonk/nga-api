package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.enums.ReplyStatus;
import com.gin.nga.params.nuke.NoticeClearParam;
import com.gin.nga.params.nuke.NoticeEnableParam;
import com.gin.nga.params.nuke.NoticeParam;
import com.gin.nga.response.body.BaseMessageBody;
import com.gin.nga.response.body.nuke.NoticeBody;
import lombok.RequiredArgsConstructor;

/**
 * 提醒消息相关API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 13:38
 */
@RequiredArgsConstructor
public class NgaNoticeApi {
    private final NgaClient client;
    /**
     * 关闭 / 开启某一楼提醒消息, 关闭后回复状态中将出现 {@link  ReplyStatus} 的 NO_HINT 属性
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @author bx002
     * @since 2023/4/19 14:17
     */
    public NgaJsonCall<BaseMessageBody> enable(NoticeEnableParam param) {
        return client.nuke( param,  BaseMessageBody.class);
    }

    /**
     * 查询提醒消息
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.NoticeBody.Res>
     * @since 2023/4/18 9:26
     */
    public NgaJsonCall<NoticeBody.Res> get() {
        return client.nuke(new NoticeParam(), NoticeBody.Res.class);
    }
    /**
     * 清空提醒消息
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.BaseMessageBody>
     * @author bx002
     * @since 2023/4/18 13:48
     */
    public NgaJsonCall<BaseMessageBody> clear(){return client.nuke(new NoticeClearParam(), BaseMessageBody.class);}
}   
