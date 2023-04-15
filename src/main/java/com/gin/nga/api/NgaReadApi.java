package com.gin.nga.api;

import com.gin.common.utils.MapUtils;
import com.gin.nga.call.NgaDocCall;
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
     * 获取单个回复内容
     * @param replyId 回复id (pid)
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ReadBody>
     * @since 2023/4/13 11:25
     */
    public NgaJsonCall<ReadBody> readReply(long replyId) {
        return read(MapUtils.singleEntry("pid", replyId));
    }

    /**
     * 获取单个回复内容(网页解析)
     * @param replyId 回复id (pid)
     * @return com.gin.nga.call.NgaDocCall<com.gin.nga.response.body.ReadBody>
     * @since 2023/4/14 11:34
     */
    public NgaDocCall<ReadBody> readReplyDoc(long replyId) {
        return readDoc(MapUtils.singleEntry("pid", replyId));
    }

    /**
     * 获取主题某一页内容
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ReadBody>
     * @since 2023/4/13 11:26
     */
    public NgaJsonCall<ReadBody> readTopic(ReadTopicParam param) {
        return read(param);
    }

    /**
     * 获取主题某一页内容(网页解析)
     * @param param 参数
     * @return com.gin.nga.call.NgaDocCall<com.gin.nga.response.body.ReadBody>
     * @since 2023/4/14 11:33
     */
    public NgaDocCall<ReadBody> readTopicDoc(ReadTopicParam param) {
        return readDoc(param);
    }
    /**
     * 读取主题内容
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ReadBody>
     * @author bx002
     * @since 2023/4/15 16:07
     */
    public NgaJsonCall<ReadBody> read(Object param) {
        return client.callJson(NgaPhpApi.read, param, null, ReadBody.class);
    }
    /**
     * 读取主题内容(兼容模式，通过网页解析)
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ReadBody>
     * @author bx002
     * @since 2023/4/15 16:07
     */
    public NgaDocCall<ReadBody> readDoc(Object param) {
        return client.callDoc(NgaPhpApi.read, param, null, ReadBody.class, ReadBody::new);
    }
}
