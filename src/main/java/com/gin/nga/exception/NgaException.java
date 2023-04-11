package com.gin.nga.exception;

import lombok.Getter;
import okhttp3.Call;

import java.io.IOException;

/**
 * Nga异常
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 15:37
 */
@Getter
public class NgaException extends IOException {
    final int code;
    final Call call;
    public NgaException(int code, Call call, String message) {
        super(message);
        this.code = code;
        this.call = call;
    }
}
