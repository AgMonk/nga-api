package com.gin.nga.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gin.common.utils.MapUtils;
import com.gin.jackson.utils.JacksonUtils;
import com.gin.nga.exception.NgaClientException;
import com.gin.nga.exception.NgaException;
import com.gin.nga.exception.NgaServerException;
import com.gin.nga.response.NgaRes;
import lombok.Setter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 抽象回调
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 15:39
 */
public abstract class AbstractCallback<T> implements Callback {
    @Setter
    Class<T> eClass;

    public static ResponseBody body(@NotNull Call call, @NotNull Response response) throws IOException {
        final int code = response.code();
        final int co = code / 100;
        final ResponseBody body = response.body();
        //noinspection EnhancedSwitchMigration
        switch (co) {
            case 3:
                throw new NgaClientException(code, call, null);
            case 2:
                return body;
            case 4:
                if (body == null) {
                    throw new NgaClientException(code, call, null);
                }
                final String s = decodeGbk(body);
                if (s.contains("{")) {
                    throw new NgaClientException(code, call, NgaRes.parse(s, Void.class).getError());
                }
                throw new NgaClientException(code, call, MapUtils.singleEntry(0, s));
            case 5:
                throw new NgaServerException(code, call, "服务器异常");
            default:
                throw new NgaException(code, call, "非预期的code");
        }
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        e.printStackTrace();
    }

    @Override
    public final void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        try {
            try (ResponseBody body = body(call, response)) {
                if (body != null) {
                    onSuccess(parse(decodeGbk(body).replace("\t", "||")));
                }
            }
        } catch (NgaException e) {
            handleException(e);
        }
    }

    /**
     * 将字符串解析为指定类型对象
     * @param resString body
     * @return 对象
     * @throws JsonProcessingException 解析错误
     * @throws NgaServerException 服务器错误
     * @throws NgaClientException 客户端错误
     */
    public abstract T parse(String resString) throws JsonProcessingException, NgaException;

    /**
     * 执行成功回调
     * @param body body字符串
     */
    public abstract void onSuccess(T body);

    /**
     * 处理异常
     * @param e 异常
     */
    public void handleException(NgaException e) {
        if (e instanceof NgaClientException) {
            NgaClientException ex = (NgaClientException) e;
            JacksonUtils.printPretty(ex.getReason());
        }
        e.printStackTrace();
    }

    /**
     * GBK解码
     * @param body body
     * @return 解码后字符串
     * @throws IOException 异常
     */
    public static String decodeGbk(ResponseBody body) throws IOException {
        final String s = new String(body.bytes(), Charset.forName("GB18030"));
        return s.replace(new String(Character.toChars(20)), "");
    }
}
