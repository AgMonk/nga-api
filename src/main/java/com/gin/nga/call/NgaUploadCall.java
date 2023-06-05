package com.gin.nga.call;

import com.gin.nga.callback.UploadCallback;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.body.UploadBody;
import okhttp3.Call;

import java.io.IOException;

/**
 * 上传Call
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 16:14
 */
public class NgaUploadCall extends NgaCall<UploadBody>{
    public NgaUploadCall(Call call) {
        super(call, UploadBody.class);
    }
    /**
     * 异步请求
     * @param callback 回调方法
     */
    public void async(UploadCallback callback){
        this.call.enqueue(callback);
    }
    /**
     * 同步请求
     * @return com.gin.nga.response.body.UploadBody
     * @author bx002
     * @since 2023/4/19 16:20
     */
    public UploadBody sync() throws IOException {
        final String s = this.syncString();
        if (s == null) {
            return null;
        }
        final UploadBody body = NgaRes.MAPPER.readValue(s, responseClass);
        UploadCallback.handleException(body);
        return body;
    }
}
