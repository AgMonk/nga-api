package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
import com.gin.nga.client.NgaClient;
import com.gin.nga.utils.DiceResult;
import com.gin.nga.utils.DiceUtils;

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

        long seed = 39841854+36451114+696459913;

        long s = seed;
        for (int i = 0; i < 15; i++) {
            final DiceResult result = DiceUtils.rnd(s);
            System.out.println(result);
            s = result.seed();
        }
    }
}
