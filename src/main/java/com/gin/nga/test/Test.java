package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.jackson.utils.JacksonUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.method.ReadApi;
import com.gin.nga.params.read.ReadTopicParam;
import com.gin.nga.response.body.ReadBody;
import com.gin.nga.response.field.ReplyInfo;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * 测试
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 10:56
 */
public class Test {
    public static final Pattern PATTERN = Pattern.compile("ubbcode\\.smiles = (.+?)//s");

    public static void main(String[] args) throws IOException {
        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie.txt"));
        final NgaClient client = new NgaClient(cookie);

        String url = "https://bbs.nga.cn/read.php?tid=36451114";
        final ReadBody readBody = ReadApi.readTopic(client, new ReadTopicParam(36451114, 1)).sync();
        final Collection<ReplyInfo> values = readBody.getReplies().values();
        JacksonUtils.printPretty(values);
    }
}
