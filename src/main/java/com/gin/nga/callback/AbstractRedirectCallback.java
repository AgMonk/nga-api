package com.gin.nga.callback;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 重定向回调
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/16 17:37
 **/
public abstract class AbstractRedirectCallback implements Callback {
    @Override
    public final void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.code()==302) {
            onRedirect(response.header("Location"));
            response.close();
        }
    }
    /**
     * 重定向回调
     * @param location 重定向地址
     */
    protected abstract void onRedirect(String location);
}
