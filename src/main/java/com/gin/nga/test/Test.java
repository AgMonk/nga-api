package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.method.ReadApi;
import com.gin.nga.params.read.ReadTopicParam;
import com.gin.nga.response.body.ReadBody;

import java.io.File;
import java.io.IOException;

/**
 * 测试
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 10:56
 */
public class Test {
    public static void main(String[] args) throws IOException {
//        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie2.txt"));
//        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie1.txt"));
        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie.txt"));

        final NgaClient ngaClient = new NgaClient(cookie);

        final ReadBody res = ReadApi.readTopicDoc(ngaClient, new ReadTopicParam(25968165, 1)).sync();

        FileIoUtils.writeObj(new File(String.format("./test/%s_%d.json", res.getClass().getSimpleName(), System.currentTimeMillis() / 1000)),res);

    }
}
