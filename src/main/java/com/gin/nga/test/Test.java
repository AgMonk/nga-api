package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
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
        final String cookie = FileIoUtils.readStr(new File("D:\\Working\\nga-cookie.txt"));
        final NgaClient client = new NgaClient(cookie);

        String url = "http://img.nga.178.com/avatars/2002/558/963/003/60175704_0.jpg?9%7C.a/60175704_1.jpg?26";
    }
}
