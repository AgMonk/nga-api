package com.gin.nga.exception;

import okhttp3.Call;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 15:42
 */
public class NgaServerException extends NgaException {

    public NgaServerException(int code, Call call, String message) {
        super(code, call, message);
    }
}
