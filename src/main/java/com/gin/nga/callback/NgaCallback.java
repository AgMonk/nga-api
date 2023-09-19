package com.gin.nga.callback;


import com.gin.nga.exception.NgaException;
import lombok.NonNull;
import okhttp3.Call;

import java.io.IOException;

/**
 * 回调方法
 *
 * @param <T> 数据类型
 */
public interface NgaCallback<T> {
    /**
     * 请求成功回调
     *
     * @param data 数据
     */
    void onSuccess(T data);

    /**
     * 请求之前执行的操作
     */
    default void beforeRequest() {
    }

    /**
     * 请求失败回调(一般为网络访问错误)
     *
     * @param call call
     * @param e    错误
     */
    default void onFailure(@NonNull Call call, @NonNull IOException e) {
        e.printStackTrace();
    }

    /**
     * 处理Nga异常
     *
     * @param e 异常
     */
    default void handleException(NgaException e) {
        e.printStackTrace();
    }
}