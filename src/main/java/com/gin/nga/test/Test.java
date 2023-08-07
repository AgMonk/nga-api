package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.jackson.utils.JacksonUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.enums.FollowType;
import com.gin.nga.method.NukeApi;
import com.gin.nga.params.nuke.follow.FollowParam;
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
        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie.txt"));
        final NgaClient client = new NgaClient(cookie);

        final FollowParam param = new FollowParam(FollowType.UNFOLLOW_USER, 25020670);
        final BaseMessageBody sync = NukeApi.follow(client, param).sync();
        JacksonUtils.printPretty(sync);
    }
}
