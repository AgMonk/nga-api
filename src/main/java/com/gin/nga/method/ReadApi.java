package com.gin.nga.method;

import com.gin.common.utils.MapUtils;
import com.gin.nga.call.NgaDocCall;
import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.client.NgaClient;
import com.gin.nga.params.read.ReadTopicParam;
import com.gin.nga.response.body.ReadBody;

import java.io.IOException;

/**
 * read.php的API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 15:49
 */
public class ReadApi {
    /**
     * 获取单个回复内容
     * @param client 客户端
     * @param replyId 回复id (pid)
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ReadBody>
     * @since 2023/4/13 11:25
     */
    public static NgaJsonCall<ReadBody> readReply(NgaClient client,long replyId) {
        return client.read(MapUtils.singleEntry("pid", replyId));
    }

    /**
     * 获取单个回复内容(网页解析)
     * @param client 客户端
     * @param replyId 回复id (pid)
     * @return com.gin.nga.call.NgaDocCall<com.gin.nga.response.body.ReadBody>
     * @since 2023/4/14 11:34
     */
    public static NgaDocCall<ReadBody> readReplyDoc(NgaClient client,long replyId) {
        return client.readDoc(MapUtils.singleEntry("pid", replyId));
    }

    /**
     * 获取主题某一页内容
     * @param client 客户端
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ReadBody>
     * @since 2023/4/13 11:26
     */
    public static NgaJsonCall<ReadBody> readTopic(NgaClient client,ReadTopicParam param) {
        return client.read(param);
    }

    /**
     * 获取主题某一页内容(网页解析)
     * @param client 客户端
     * @param param 参数
     * @return com.gin.nga.call.NgaDocCall<com.gin.nga.response.body.ReadBody>
     * @since 2023/4/14 11:33
     */
    public static NgaDocCall<ReadBody> readTopicDoc(NgaClient client,ReadTopicParam param) {
        return client.readDoc(param);
    }

    public static String toReply(NgaClient client,long replyId) throws IOException {
        return client.toReply(replyId);
    }
}   
