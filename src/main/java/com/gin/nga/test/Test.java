package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.nga.client.NgaClient;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

//        JacksonUtils.printPretty(NukeApi.followStatus(client, 1).sync());
        String s = "Sat, 12 Aug 2023 03:37:11 GMT";
        System.out.println(ZonedDateTime.parse(s, DateTimeFormatter.RFC_1123_DATE_TIME));
    }
}
