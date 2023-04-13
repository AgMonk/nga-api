package com.gin.nga.api;

import com.gin.common.utils.MapUtils;
import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.params.read.ReadTopicParam;
import com.gin.nga.response.body.ReadBody;
import lombok.RequiredArgsConstructor;

/**
 * 主题内容API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:21
 */
@RequiredArgsConstructor
public class NgaReadApi {
    private final NgaClient client;
/**
 * 获取主题某一页内容
 * @param param 参数
 * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ReadBody>
 * @author bx002
 * @since 2023/4/13 11:26
 */
    public NgaJsonCall<ReadBody> readTopic(ReadTopicParam param){
        return read(param);
    }

    /**
     * 获取单个回复内容
     * @param replyId 回复id (pid)
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ReadBody>
     * @author bx002
     * @since 2023/4/13 11:25
     */
    public NgaJsonCall<ReadBody> readReply(long replyId){
        return read(MapUtils.singleEntry("pid",replyId));
    }


    private NgaJsonCall<ReadBody> read(Object param){
        return client.callJson(NgaPhpApi.read,param,null,ReadBody.class);
    }
}   
