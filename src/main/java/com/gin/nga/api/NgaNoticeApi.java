package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.enums.ReplyStatus;
import com.gin.nga.params.nuke.NoticeParam;
import com.gin.nga.response.body.BaseMessageBody;
import com.gin.nga.response.body.nuke.NoticeBody;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

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
     * @param topicId 主题id
     * @param replyId 回复id
     * @param enable  true = 开启 false = 关闭
     * @return com.gin.nga.call.NgaJsonCall<BaseMessageBody>
     * @since 2023/4/18 10:59
     */
    public NgaJsonCall<BaseMessageBody> enable(long topicId, long replyId, boolean enable) {
        final HashMap<String, Serializable> param = new HashMap<>(5);
        param.put("func", "noti_tag");
        param.put("no_hint", enable ? 0 : 1);
        param.put("tid", topicId);
        param.put("pid", replyId);
        param.put("raw", 3);
        return client.callJson(NgaPhpApi.nuke, param, null, BaseMessageBody.class);
    }

    /**
     * 查询提醒消息
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.NoticeBody.Res>
     * @since 2023/4/18 9:26
     */
    public NgaJsonCall<NoticeBody.Res> get() {
        return client.nuke(new NoticeParam(), NoticeBody.Res.class);
    }
}   
