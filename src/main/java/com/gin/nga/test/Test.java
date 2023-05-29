package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.nga.bbscode.utils.BbsTagParser;
import com.gin.nga.client.NgaClient;

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

//        final ThreadBody res = ThreadApi.forumList(ngaClient, new ForumListParam(650, 1, null)).sync();

//        JacksonUtils.printPretty(res.getTopics().values());

        File file = new File("D:\\test\\新建文本文档.txt");

        final String content = FileIoUtils.readStr(file);

        BbsTagParser.parseContent(content);

    }
}
