package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.common.utils.JacksonUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.method.NukeApi;
import com.gin.nga.method.PostApi;
import com.gin.nga.params.UploadParam;
import com.gin.nga.params.post.PrepareParam;
import com.gin.nga.response.post.PrepareBody;

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
        final String url = "https://img8.nga.cn/attach.php";
//        JacksonUtils.printPretty(ReadApi.readReply(ngaClient,460443749).sync());
        final File file = new File("C:\\Users\\bx002\\Desktop\\3.html");

        final PrepareParam prepareParam = PrepareParam.newTopicParam(-547859L,null);
        final PrepareBody prepareBody = PostApi.prepare(client, prepareParam).sync();
        final UploadParam uploadParam = new UploadParam(file,prepareBody.getAuth(),-547859L);
        JacksonUtils.printPretty(NukeApi.attachUpload(client,prepareBody.getAttachUrl(), uploadParam).sync());
    }
}
