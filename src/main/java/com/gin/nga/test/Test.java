package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.common.utils.JacksonUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.method.ThreadApi;
import com.gin.nga.params.thread.ForumListParam;
import com.gin.nga.response.body.ThreadBody;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 测试
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 10:56
 */
public class Test {
    public static final Pattern PATTERN = Pattern.compile("ubbcode\\.smiles = (.+?)//s");
    public static void main(String[] args) throws IOException {
//        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie2.txt"));
//        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie1.txt"));
        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie.txt"));

        final NgaClient ngaClient = new NgaClient(cookie);

        final ThreadBody res = ThreadApi.forumList(ngaClient, new ForumListParam(-547859, 1, null)).sync();

        JacksonUtils.printPretty(res.getCurrentUser());


    }
}
