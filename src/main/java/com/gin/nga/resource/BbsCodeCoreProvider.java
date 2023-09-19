package com.gin.nga.resource;

import com.gin.common.utils.FileIoUtils;
import com.gin.nga.response.emote.BbsCodeBody;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * BbsCodeCore资源提供者
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/9/19 15:08
 **/
public class BbsCodeCoreProvider extends ResourceProvider<BbsCodeBody> {
    public static final String URL = "https://img4.nga.178.com/common_res/js_bbscode_core.js";

    public BbsCodeCoreProvider( OkHttpClient client,  File cacheFile, long expires) {
        super(URL, client, cacheFile, expires);
    }


    @Override
    protected BbsCodeBody readCache(File cacheFile) throws IOException {
        return FileIoUtils.readObj(cacheFile, BbsCodeBody.class);
    }

    @Override
    protected BbsCodeBody convert(@NonNull Response response) throws IOException {
        if (response.code() == 200) {
            try (ResponseBody responseBody = response.body()) {
                if (responseBody != null) {
                    return new BbsCodeBody(responseBody.string());
                }
            }
        }
        throw new IOException(String.format(Locale.CHINA, "code: %d 响应数据为空", response.code()));
    }
}
