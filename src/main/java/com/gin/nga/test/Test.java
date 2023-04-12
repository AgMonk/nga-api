package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.nga.api.NgaClient;
import com.gin.nga.api.NgaThreadApi;

import java.io.File;
import java.io.IOException;

/**
 * 测试
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 10:56
 */
public class Test {
    /**
     * thread.php 接口测试
     */
    public static void threadTest(NgaClient ngaClient) {
        final NgaThreadApi api = new NgaThreadApi(ngaClient);
        // 合集id
        long colTid = 29111018L;
        // 版面id
        long forumId = 428;
//        long forumId = -547859L;

        final ThreadTest threadTest = new ThreadTest(api, colTid, forumId, "体力");

        threadTest.colListTest();
        threadTest.colSearchTest();
        threadTest.favorTest();
        threadTest.forumListTest();
        threadTest.forumSearchTest();
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

    public static void main(String[] args) throws IOException {
        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie.txt"));

        final NgaClient ngaClient = new NgaClient(cookie);


        threadTest(ngaClient);
    }
}
