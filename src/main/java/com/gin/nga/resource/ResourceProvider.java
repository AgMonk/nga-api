package com.gin.nga.resource;


import com.fasterxml.jackson.core.type.TypeReference;
import com.gin.common.utils.FileIoUtils;
import com.gin.jackson.utils.JacksonUtils;
import com.gin.nga.callback.NgaCallback;
import lombok.NonNull;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * 静态资源提供者, 在data目录中以文件形式缓存静态资源, 并设置更新间隔, 当超出间隔时发送请求进行更新, 如果更新失败则继续返回缓存数据
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/9/19 11:58
 **/
public abstract class ResourceProvider<T> {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36";
    /**
     * 过期时长(毫秒)
     */
    private final long expires;
    @NonNull
    private final File cacheFile;
    @NonNull
    private final OkHttpClient client;

    /**
     * 请求地址
     */
    @NonNull
    private final String url;

    protected ResourceProvider(@NonNull String url, @NonNull OkHttpClient client, @NonNull File cacheFile, long expires) {
        this.expires = expires;
        this.client = client;
        this.url = url;
        this.cacheFile = cacheFile;
    }

    protected abstract T readCache(File cacheFile) throws IOException;

    /**
     * 清除缓存
     */
    public void clear() {
        // 缓存文件
        if (cacheFile.exists() && cacheFile.delete()) {
            System.out.println("删除缓存文件: " + cacheFile.getName());
        }
    }

    /**
     * 将响应转换为指定类型数据的方法
     *
     * @param response 响应
     * @return T
     */
    protected T convert(@NonNull Response response) throws IOException {
        if (response.code() == 200) {
            try (ResponseBody responseBody = response.body()) {
                if (responseBody != null) {
                    return JacksonUtils.MAPPER.readValue(responseBody.byteStream(), new TypeReference<T>() {
                    });
                }
            }
        }
        throw new IOException(String.format(Locale.CHINA, "code: %d 响应数据为空", response.code()));
    }

    /**
     * 同步请求
     *
     * @return 响应数据
     */
    public T sync(boolean ignoreCache) throws IOException {
        // 如果缓存可用
        if (!ignoreCache && isCacheAvailable(cacheFile)) {
            return readCache(cacheFile);
        }

        HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null) {
            throw new IOException("url错误: " + url);
        }
        Call call = client.newCall(buildRequest(httpUrl));
        T data = convert(call.execute());
        // 写入缓存文件
        FileIoUtils.writeObj(cacheFile, data);
        return data;
    }

    /**
     * 异步请求
     *
     * @param ignoreCache 是否忽略缓存
     * @param callback    回调
     */
    public void async(boolean ignoreCache, @NonNull NgaCallback<T> callback) {
        // 如果缓存可用
        if (!ignoreCache && isCacheAvailable(cacheFile)) {
            try {
                // 读取数据，执行回调
                callback.onSuccess(readCache(cacheFile));
                return;
            } catch (IOException e) {
                // 删除发生错误的缓存文件
                clear();
                // 发生异常，往下执行请求
                e.printStackTrace();
            }
        }
        // 执行请求之前的操作
        callback.beforeRequest();

        HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null) {
            callback.onFailure(null, new IOException("url错误: " + url));
            return;
        }
        Call call = client.newCall(buildRequest(httpUrl));
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    T data = convert(response);
                    // 写入缓存文件
                    FileIoUtils.writeObj(cacheFile, data);
                    // 执行成功回调
                    callback.onSuccess(data);
                } catch (IOException e) {
                    // 发生异常，如果缓存文件存在，尝试返回缓存文件
                    if (cacheFile.exists()) {
                        try {
                            callback.onSuccess(readCache(cacheFile));
                        } catch (IOException ex) {
                            // 缓存读取失败，执行失败回调
                            callback.onFailure(call, e);
                            // 删除发生错误的缓存文件
                            clear();
                        }
                    }
                }
            }
        });
    }

    @NonNull
    protected Request buildRequest(HttpUrl httpUrl) {
        Request request = new Request.Builder()
                .url(httpUrl)
                .header("Host", httpUrl.host())
                .header("User-Agent", USER_AGENT)
                .build();
        return request;
    }

    private boolean isCacheAvailable(File cachFile) {
        if (!cachFile.exists()) {
            return false;
        }
        return System.currentTimeMillis() - cachFile.lastModified() <= expires;
    }

    public static <T> T readJson(@NonNull Response response, Class<T> clazz) throws IOException {
        if (response.code() == 200) {
            try (ResponseBody responseBody = response.body()) {
                if (responseBody != null) {
                    return JacksonUtils.MAPPER.readValue(responseBody.byteStream(), clazz);
                }
            }
        }
        throw new IOException(String.format(Locale.CHINA, "code: %d 响应数据为空", response.code()));
    }

    public static String generateFilename(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}
