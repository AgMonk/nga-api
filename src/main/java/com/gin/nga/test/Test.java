package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.common.utils.JacksonUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.method.PostApi;
import com.gin.nga.params.UploadParam;

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

    *//**
     * 将测试结果写入文件
     * @param res      响应结果
     * @param filename 写入文件名
     *//*
    public static void writeTestRes(Object res, String filename) {
        try {
            FileIoUtils.writeObj(new File("./test/" + filename), res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readTest(NgaClient ngaClient) {
        final ReadTest readTest = new ReadTest(new NgaReadApi(ngaClient));

        readTest.test();
    }
*/
    public static void main(String[] args) throws IOException {
        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie.txt"));

        final NgaClient ngaClient = new NgaClient(cookie);

        String attachUrl = "https://img8.nga.cn/attach.php";
        final File file = new File("D:\\download\\aria2\\plu\\99147997_p0.jpg");
//        final File file = new File("E:/download/chrome/GifCam.zip");
        final String auth = "025ff03e6440916a462d62717162d0b8ab51c7565c675a16d035e74760e0";
        final UploadParam param = new UploadParam(file, auth,-547859);
        JacksonUtils.printPretty(PostApi.upload(ngaClient, attachUrl, param).sync());

    }
}
