package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.method.PostApi;
import com.gin.nga.params.post.PostParam;
import com.gin.nga.params.post.PrepareParam;
import com.gin.nga.response.post.PostBody;
import com.gin.nga.response.post.PrepareBody;

import java.io.File;
import java.io.IOException;

/**
 * 测试
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 10:56
 */
public class Test {
  /*  public static void forumTest(NgaClient ngaClient) {
        final NgaForumApi api = new NgaForumApi(ngaClient);

        final ForumParam param = new ForumParam("少女");
        api.search(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ForumBody body) {
                Test.writeTestRes(body, String.format("forum-%s.json", keyword));
            }
        });
    }

    *//**
     * thread.php 接口测试
     *//*
    @SuppressWarnings("unused")
    public static void threadTest(NgaClient ngaClient) {
        final NgaThreadApi api = new NgaThreadApi(ngaClient);
        final ThreadTest threadTest = new ThreadTest(api);
        threadTest.setTestForumList(true);
        threadTest.test();
    }

    *

    private static void readTest(NgaClient ngaClient) {
        final ReadTest readTest = new ReadTest(new NgaReadApi(ngaClient));

        readTest.test();
    }
*/
    public static void main(String[] args) throws IOException {
        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie.txt"));

        final NgaClient ngaClient = new NgaClient(cookie);

        final long topicId = 33506312;
        final long replyId = 657267493;
        final PrepareParam prepareParam = PrepareParam.replyParam(topicId, replyId,false);
        final PrepareBody prepareRes = PostApi.prepare(ngaClient, prepareParam).sync();
        writeTestRes(prepareRes,"post-prepare-modify-%d-%d.json",topicId,replyId);

        final PostBody postRes = PostApi.send(ngaClient, prepareParam, new PostParam("标题", "正文正文正文正文", true, true)).sync();
        writeTestRes(postRes,"post-send-%d-%d-%d.json",topicId,replyId,System.currentTimeMillis());

    }

    /**
     * 将测试结果写入文件
     * @param res      响应结果
     * @param format 格式
     * @param args 参数
     */
    public static void writeTestRes(Object res, String format, Object... args) {
        try {
            FileIoUtils.writeObj(new File("./test/" + String.format(format, args)), res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
