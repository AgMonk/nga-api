package com.gin.nga.call;

import okhttp3.Call;

/**
 * Nga Call
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 15:14
 */
public class NgaDocCall<T> extends NgaCall<T> {

    public NgaDocCall(Call call, Class<T> responseClass) {
        super(call, responseClass);
    }
}
