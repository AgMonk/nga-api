package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.method.NukeApi;
import com.gin.nga.params.nuke.pm.PrivateMessageNewParam;

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

    */

    /**
     * 将测试结果写入文件
     * @param res    响应结果
     * @param format 格式
     * @param args   参数
     */
    public static void writeTestRes(Object res, String format, Object... args) {
        try {
            FileIoUtils.writeObj(new File("./test/" + String.format(format, args)), res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
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

        final int messageId = 1231981;
//        final int messageId = 4261507;
        final int page = 1;
        writeTestRes(NukeApi.privateMessageNew(ngaClient, new PrivateMessageNewParam("标题标题标题","正文正文",63020118L)).sync(), "private-message-new-%d.json", 63020118L);


    }
}
