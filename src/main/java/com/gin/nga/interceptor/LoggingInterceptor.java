package com.gin.nga.interceptor;

import com.gin.common.utils.TimeUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 14:34
 **/
public class LoggingInterceptor implements Interceptor {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String TAG = "DEBUG";

    @NotNull
    private static String getTimeString() {
        return DATE_TIME_FORMATTER.format(ZonedDateTime.now().withZoneSameInstant(TimeUtils.CHINESE_ZONE_ID));
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long startTime = System.nanoTime();
        System.out.printf("[%s] [%s] 发送请求 [%s] %s on %s\n",
                TAG,
                getTimeString(),
                request.method(),
                request.url(),
                chain.connection()
        );

        Response response = chain.proceed(request);

        long endTime = System.nanoTime();
        System.out.printf("[%s] [%s] 收到响应 [%s] code:%d %s in %.1fms\n",
                TAG,
                getTimeString(),
                request.method(),
                response.code(),
                response.request().url(),
                (endTime - startTime) / 1e6d
        );
        if (response.code() / 100 == 3) {
            System.out.printf("[%s] [%s] location: %s\n", TAG, getTimeString(), response.header("location"));
        }
        return response;
    }
}
