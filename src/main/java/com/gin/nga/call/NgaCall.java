package com.gin.nga.call;

import com.gin.nga.callback.AbstractCallback;
import lombok.RequiredArgsConstructor;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 15:32
 */
@RequiredArgsConstructor
public class NgaCall<T> {
    final Call call;
    final Class<T> responseClass;

    /**
     * 同步请求，返回字符串
     * @return 响应字符串
     * @throws IOException 异常
     */
    public String syncString() throws IOException {
        try (final Response response = this.call.execute()){
            final ResponseBody body = AbstractCallback.body(call, response);
            if (body == null) {
                return null;
            }
            return  AbstractCallback.decodeGbk(body);
        }
    }
}   
