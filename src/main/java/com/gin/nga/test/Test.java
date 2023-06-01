package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.common.utils.JacksonUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.exception.NgaClientException;
import com.gin.nga.method.NukeApi;
import com.gin.nga.params.nuke.base.VoteParam;
import com.gin.nga.response.body.BaseMessageBody;

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

//        final ReadBody body = ReadApi.readTopicDoc(ngaClient, new ReadTopicParam(36478736, 1)).sync();
//        JacksonUtils.printPretty(body.getReplies().get(0).getVoteData());

        try {
            final BaseMessageBody res = NukeApi.vote(ngaClient, new VoteParam(36478736, 107959, 222)).sync();
            JacksonUtils.printPretty(res);
        } catch (NgaClientException e) {
            JacksonUtils.printPretty(e.getReason());
        }
    }
}
