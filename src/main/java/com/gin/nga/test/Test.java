package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.nga.api.*;
import com.gin.nga.callback.JsonCallback;
import com.gin.nga.params.forum.ForumParam;
import com.gin.nga.response.body.ForumBody;

import java.io.File;
import java.io.IOException;

/**
 * 测试
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 10:56
 */
public class Test {
    public static void forumTest(NgaClient ngaClient) {
        final NgaForumApi api = new NgaForumApi(ngaClient);

        final ForumParam param = new ForumParam();
        final String keyword = "少女";
        param.setKeyword(keyword);
        api.search(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ForumBody body) {
                Test.writeTestRes(body, String.format("forum-%s.json", keyword));
            }
        });
    }

    /**
     * thread.php 接口测试
     */
    @SuppressWarnings("unused")
    public static void threadTest(NgaClient ngaClient) {
        final NgaThreadApi api = new NgaThreadApi(ngaClient);
        final ThreadTest threadTest = new ThreadTest(api);
        threadTest.setTestForumList(true);
        threadTest.test();
    }

    /**
     * 将测试结果写入文件
     * @param res      响应结果
     * @param filename 写入文件名
     */
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

    public static void main(String[] args) throws IOException {
        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie.txt"));

        final NgaClient ngaClient = new NgaClient(cookie);


//        threadTest(ngaClient);
//        forumTest(ngaClient);
//        readTest(ngaClient);
        nukeTest(ngaClient);
    }

    public static void nukeTest( NgaClient ngaClient){
        final NukeTest nukeTest = new NukeTest(new NgaNukeApi(ngaClient));

        nukeTest.testGetUserInfo();
    }
}
