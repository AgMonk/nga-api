package com.gin.nga.exception;

import okhttp3.Call;

import java.util.Map;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 15:42
 */
public class NgaClientException extends NgaException {
    final Map<Integer,String> reason;

    public NgaClientException(int code, Call call,  Map<Integer,String> reason) {
        super(code, call, "客户端错误");
        this.reason = reason;
    }
}
