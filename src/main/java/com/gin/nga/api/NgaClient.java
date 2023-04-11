package com.gin.nga.api;

import com.gin.nga.enums.NgaDomain;
import com.gin.nga.exception.IllegalCookieException;
import com.gin.nga.interceptor.LoggingInterceptor;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Nga客户端
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 11:39
 */
public class NgaClient {
    /**
     * 请求编码
     */
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    /**
     * 从cookie中解析uid的正则表达式
     */
    private static final Pattern UID_PATTERN = Pattern.compile("ngaPassportUid=(\\d+);");
    /**
     * 从cookie中解析用户名的正则表达式
     */
    private static final Pattern USERNAME_PATTERN = Pattern.compile("ngaPassportUrlencodedUname=(.+?);");
    /**
     * 客户端
     */
    final OkHttpClient client;
    /**
     * 请求的域名
     */
    final NgaDomain domain;
    /**
     * cookie
     */
    @NotNull
    final String cookie;
    /**
     * 当前用户id
     */
    @Getter
    long userId;
    /**
     * 当前用户名
     */
    @Getter
    String username;

    public NgaClient(@NotNull String cookie, NgaDomain domain) throws IOException {
        this(cookie, null, domain);
    }

    public NgaClient(@NotNull String cookie, OkHttpClient client) throws IOException {
        this(cookie, client, null);
    }

    public NgaClient(@NotNull String cookie) throws IOException {
        this(cookie, null, null);
    }

    public NgaClient(@NotNull String cookie, OkHttpClient client, NgaDomain domain) throws IllegalCookieException {
        this.cookie = cookie;
        this.client = client != null ? client : getOkHttpClient();
        this.domain = domain != null ? domain : NgaDomain.cn;

        final IllegalCookieException e = new IllegalCookieException();

        // 解析UID
        {
            final Matcher matcher = UID_PATTERN.matcher(cookie);
            if (matcher.find()) {
                this.userId = Long.parseLong(matcher.group(1));
            } else {
                throw e;
            }
        }
        // 解析用户名
        {
            final Matcher matcher = USERNAME_PATTERN.matcher(cookie);
            if (matcher.find()) {
                // gbk解码
                this.username = URLDecoder.decode(matcher.group(1), Charset.forName("gbk"));
            } else {
                throw e;
            }
        }

        System.out.printf("NGA客户端启动 uid: %d 用户名: %s",this.userId,this.username);
    }


    /**
     * 默认客户端
     * @return 客户端
     */
    @NotNull
    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .readTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS).build();
    }
}
