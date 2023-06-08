package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.common.utils.JacksonUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.method.NukeApi;
import com.gin.nga.response.body.BaseMessageBody;
import com.gin.nga.response.body.nuke.BlockDataBody;
import com.gin.nga.response.field.BlockData;

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

        final NgaClient client = new NgaClient(cookie);

        final BlockDataBody body = NukeApi.blockDataList(client).sync();
        JacksonUtils.printPretty(body);
        final BlockData data = body.getData();
        data.getKeywords().remove(0);
        final BaseMessageBody res = NukeApi.blockDataSet(client, data).sync();
        System.out.println(res.getMessage());
        JacksonUtils.printPretty(NukeApi.blockDataList(client).sync());
    }
}
