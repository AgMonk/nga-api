package com.gin.nga.callback;

import com.gin.nga.exception.NgaClientException;
import com.gin.nga.exception.NgaException;
import com.gin.nga.exception.NgaServerException;
import com.gin.nga.response.NgaRes;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 抽象回调
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 15:39
 */
public abstract class AbstractCallback {

    public static ResponseBody body(@NotNull Call call, @NotNull Response response) throws IOException {
        final int code = response.code();
        final int co = code / 100;
        final ResponseBody body = response.body();
        switch (co) {
            case 3, 2 -> {
                return body;
            }
            case 4 -> {
                if (body == null) {
                    throw new NgaClientException(code, call, null);
                }
                throw new NgaClientException(code, call, NgaRes.parse(body.string(), Void.class).getError());
            }
            case 5 -> throw new NgaServerException(code, call, "服务器异常");
            default -> throw new NgaException(code, call, "非预期的code");
        }
    }

}
