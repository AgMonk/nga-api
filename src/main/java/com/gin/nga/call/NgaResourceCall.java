package com.gin.nga.call;

import com.gin.nga.callback.ResourceCallback;
import com.gin.nga.interfaze.ResourceParser;
import okhttp3.Call;

import java.io.IOException;

/**
 * 静态资源call
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 10:17
 */
public class NgaResourceCall<T> extends NgaCall<T>{
    final ResourceParser<T> parser;
    public NgaResourceCall(Call call, Class<T> responseClass, ResourceParser<T> parser) {
        super(call, responseClass);
        this.parser = parser;
    }

    /**
     * 异步请求
     * @param callback 回调方法
     */
    public void async(ResourceCallback<T> callback){
        callback.setEClass(responseClass);
        callback.setParser(parser);
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
        return parser.parse(s, responseClass);
    }
}
