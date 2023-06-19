package com.gin.nga.test;

import com.gin.common.utils.FileIoUtils;
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

//        final NgaClient client = new NgaClient(cookie);


        long seed = 39841854+35630533+697390033+1;

        final DiceResult d1 = DiceUtils.dice(seed, 100);
        System.out.println("d1 = " + d1);
        final DiceResult d2 = DiceUtils.dice(d1.seed(), 100);
        System.out.println("d2 = " + d2);
        final DiceResult d3 = DiceUtils.dice(d2.seed(), 100);
        System.out.println("d3 = " + d3);

    }
}
