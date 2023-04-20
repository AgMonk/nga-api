package com.gin.nga.call;

import com.gin.nga.callback.JsonCallback;
import com.gin.nga.exception.NgaClientException;
import com.gin.nga.response.NgaRes;
import okhttp3.Call;

import java.io.IOException;

/**
 * Nga Call
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 15:14
 */
public class NgaJsonCall<T> extends NgaCall<T>{
    public NgaJsonCall(Call call, Class<T> responseClass) {
        super(call, responseClass);
    }

    /**
     * 异步请求
     * @param callback 回调方法
     */
    public void async(JsonCallback<T> callback){
        callback.setEClass(responseClass);
        this.call.enqueue(callback);
    }

    /**
     * 同步请求
     * @return {@link T}
     */
    public T sync() throws IOException {
        final String s = this.syncString();
        if (s == null) {
            return null;
        }
        final NgaRes<T> res = NgaRes.parse(s, responseClass);
        if (res.getData()==null) {
            throw new NgaClientException(400, call, res.getError());
        }
        return res.getData();
    }
}
