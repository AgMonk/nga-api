package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.jackson.utils.JacksonUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.method.ReadApi;
import com.gin.nga.params.read.ReadTopicParam;
import com.gin.nga.response.body.ReadBody;

import java.io.File;
import java.io.IOException;
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
//        testTopic(37516420);
        testTopic(37529736);
    }


    private static void testTopic(long topicId){
        try {
            final ReadTopicParam param = new ReadTopicParam(topicId,1);
            final ReadBody readBody = ReadApi.readTopic(getClient(), param).sync();
            JacksonUtils.printPretty(readBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static NgaClient getClient(){
        try {
            return new NgaClient(FileIoUtils.readStr(new File("D:\\Working\\nga-cookie2.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
