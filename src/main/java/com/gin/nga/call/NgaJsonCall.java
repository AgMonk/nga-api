package com.gin.nga.call;

import okhttp3.Call;

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
}
