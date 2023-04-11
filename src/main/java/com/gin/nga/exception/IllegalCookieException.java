package com.gin.nga.exception;

import java.io.IOException;

/**
 * 非法的COOKIE异常
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 12:47
 */
public class IllegalCookieException extends IOException {
    public IllegalCookieException() {
        super("非法的Cookie, 无法识别UID或用户名");
    }
}
