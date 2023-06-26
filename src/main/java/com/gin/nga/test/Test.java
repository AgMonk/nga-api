package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.method.PostApi;
import com.gin.nga.params.post.PostParam;
import com.gin.nga.params.post.PrepareParam;

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

        final PrepareParam prepareParam =PrepareParam.replyParam(36451114,0,false);
        PostApi.send(client, prepareParam, new PostParam("测试自动字数补丁","[@39841854][@25020670][@左牵黄右擒苍]")).sync();
    }
}
