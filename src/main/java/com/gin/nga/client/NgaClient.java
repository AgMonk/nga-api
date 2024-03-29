package com.gin.nga.client;

import com.gin.jackson.utils.JacksonUtils;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.call.NgaDocCall;
import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.call.NgaUploadCall;
import com.gin.nga.callback.AbstractRedirectCallback;
import com.gin.nga.enums.NgaDomain;
import com.gin.nga.enums.NgaPhpApi;
import com.gin.nga.exception.IllegalCookieException;
import com.gin.nga.interceptor.LoggingInterceptor;
import com.gin.nga.interfaze.DocumentParser;
import com.gin.nga.params.PageParam;
import com.gin.nga.params.UploadParam;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import com.gin.nga.params.nuke.base.NukeFuncParam;
import com.gin.nga.response.body.ReadBody;
import com.gin.nga.response.body.ThreadBody;
import lombok.Getter;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Nga客户端
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 11:39
 */
public class NgaClient {
    private static final String UA = "NGA_WP_JW";
    /**
     * 从cookie中解析uid的正则表达式
     */
    private static final Pattern UID_PATTERN = Pattern.compile("ngaPassportUid=(\\d+);");
    private static final Pattern UID_PATTERN_2 = Pattern.compile("ngaPassportUid=(\\d+)$");
    /**
     * 从cookie中解析用户名的正则表达式
     */
    private static final Pattern USERNAME_PATTERN = Pattern.compile("ngaPassportUrlencodedUname=(.+?);");
    private static final Pattern USERNAME_PATTERN_2 = Pattern.compile("ngaPassportUrlencodedUname=(.+?)$");
    /**
     * 从cookie中解析令牌的正则表达式
     */
    private static final Pattern CID_PATTERN = Pattern.compile("ngaPassportCid=(.+?);");
    private static final Pattern CID_PATTERN_2 = Pattern.compile("ngaPassportCid=(.+?)$");
    /**
     * 客户端
     */
    final OkHttpClient client;
    /**
     * 请求的域名
     */
    @Getter
    final NgaDomain ngaDomain;
    /**
     * cookie
     */
    @NotNull
    @Getter
    final String cookie;
    /**
     * 当前用户id
     */
    @Getter
    final long userId;
    /**
     * 当前用户名
     */
    @Getter
    final String username;
    /**
     * 令牌
     */
    @Getter
    final String cid;

    final String urlencodedUname;

    @SuppressWarnings("unused")
    public NgaClient(@NotNull String cookie, NgaDomain ngaDomain) throws IOException {
        this(cookie, null, ngaDomain);
    }

    @SuppressWarnings("unused")
    public NgaClient(@NotNull String cookie, OkHttpClient client) throws IOException {
        this(cookie, client, null);
    }

    public NgaClient(@NotNull String cookie) throws IOException {
        this(cookie, null, null);
    }

    public NgaClient(@NotNull String cookie, OkHttpClient client, NgaDomain ngaDomain) throws IllegalCookieException {
        this.client = (client != null ? client : getOkHttpClient()).newBuilder()
                .followRedirects(false).build();
        this.ngaDomain = ngaDomain != null ? ngaDomain : NgaDomain.cn;

        if (!isCookieLegal(cookie)){
            throw new IllegalCookieException();
        }
        this.userId = Long.parseLong(parseUserId(cookie));
        this.urlencodedUname = parseUsername(cookie);
        this.username = decodeUsername(this.urlencodedUname);
        this.cid = parseCid(cookie);

        this.cookie = String.format("ngaPassportUid=%d; ngaPassportUrlencodedUname=%s; ngaPassportCid=%s; ",
                this.userId,
                this.urlencodedUname,
                this.cid);

        System.out.printf("NGA客户端启动 uid: %d 用户名: %s\n", this.userId, this.username);
    }

    /**
     * 生成 FormBody
     *
     * @param formData 表单数据
     * @return FormBody
     */
    private static FormBody getFormBody(Object formData) {
        final FormBody.Builder builder = new FormBody.Builder();
        if (formData != null) {
            final HashMap<String, Object> map = JacksonUtils.jsonToMap(formData);
            map.forEach((k, v) -> {
                if (v != null) {
                    builder.add(k, String.valueOf(v));
                }
            });
        }
        return builder.build();
    }

    private static MultipartBody getMultipartBody(UploadParam param) {
        final MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        JacksonUtils.jsonToMap(param).forEach((k, v) -> {
            if (v != null) {
                builder.addFormDataPart(k, String.valueOf(v));
            }
        });
        final File file = param.getFile();
        RequestBody fileBody = RequestBody.create(file, MediaType.parse("application/octet-stream"));
        builder.addFormDataPart(UploadParam.PREFIX, file.getName(), fileBody);
        return builder.build();
    }

    /**
     * 默认客户端
     *
     * @return 客户端
     */
    @NotNull
    public static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .readTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS).build();
    }

    /**
     * 原生call
     *
     * @param phpApi     phpApi
     * @param queryParam 参数
     * @param formData   表单数据
     * @param json       是否请求json格式数据
     * @return call
     */
    public Call call(NgaPhpApi phpApi, Object queryParam, Object formData, boolean json) {
        final HttpUrl httpUrl = getHttpUrl(phpApi, queryParam, json);
        final FormBody formBody = getFormBody(formData);
        final Request request = getRequest(httpUrl, formBody);
        return this.client.newCall(request);
    }

    /**
     * 请求网页数据
     *
     * @param phpApi         phpApi
     * @param queryParam     参数
     * @param formData       表单数据
     * @param responseClass  响应类型
     * @param documentParser 网页数据解析器
     * @return call
     */
    public <T> NgaDocCall<T> callDoc(NgaPhpApi phpApi, Object queryParam, Object formData, Class<T> responseClass, DocumentParser<T> documentParser) {
        final Call call = call(phpApi, queryParam, formData, false);
        return new NgaDocCall<>(call, responseClass, documentParser);
    }

    /**
     * 请求json数据
     *
     * @param phpApi        phpApi
     * @param queryParam    参数
     * @param formData      表单数据
     * @param responseClass 响应类型
     * @return call
     */
    public <T> NgaJsonCall<T> callJson(NgaPhpApi phpApi, Object queryParam, Object formData, Class<T> responseClass) {
        final Call call = call(phpApi, queryParam, formData, true);
        return new NgaJsonCall<>(call, responseClass);
    }

    /**
     * 上传call
     *
     * @param attachUrl 上传地址
     * @param param     参数
     * @return com.gin.nga.call.NgaUploadCall
     * @since 2023/4/19 16:50
     */
    public NgaUploadCall callUpload(String attachUrl, UploadParam param) {
        final HttpUrl httpUrl = HttpUrl.parse(attachUrl);
        final MultipartBody multipartBody = getMultipartBody(param);
        assert httpUrl != null;
        final Request request = new Request.Builder()
                .url(httpUrl)
                .post(multipartBody)
                .build();
        return new NgaUploadCall(this.client.newCall(request));
    }

    public <T> NgaJsonCall<T> nuke(NukeFuncParam param, Class<T> responseClass) {
        return callJson(NgaPhpApi.nuke, param, null, responseClass);
    }

    /**
     * nuke请求
     *
     * @param param         参数
     * @param responseClass 响应类型
     * @return call
     */
    public <T> NgaJsonCall<T> nuke(NukeBaseParam param, Class<T> responseClass) {
        return callJson(NgaPhpApi.nuke, param, null, responseClass);
    }

    /**
     * post请求
     *
     * @param param         参数
     * @param formData      formData
     * @param responseClass 响应类型
     * @return com.gin.nga.call.NgaJsonCall<T>
     * @author bx002
     * @since 2023/4/20 9:55
     */
    public <T> NgaJsonCall<T> post(Object param, Object formData, Class<T> responseClass) {
        return callJson(NgaPhpApi.post, param, formData, responseClass);
    }

    /**
     * 读取主题内容
     *
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ReadBody>
     * @since 2023/4/15 16:07
     */
    public NgaJsonCall<ReadBody> read(Object param) {
        return callJson(NgaPhpApi.read, param, null, ReadBody.class);
    }

    /**
     * 读取主题内容(兼容模式，通过网页解析)
     *
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.ReadBody>
     * @since 2023/4/15 16:07
     */
    public NgaDocCall<ReadBody> readDoc(Object param) {
        return callDoc(NgaPhpApi.read, param, null, ReadBody.class, ReadBody::new);
    }

    /**
     * 查询主题列表
     *
     * @param queryParam 查询参数
     * @return Call
     */
    public NgaJsonCall<ThreadBody> thread(PageParam queryParam) {
        return callJson(NgaPhpApi.thread, queryParam, null, ThreadBody.class);
    }

    /**
     * 请求一个回复Id的具体地址,使用to参数获取重定向地址
     *
     * @param replyId 回复id
     * @return 具体地址
     */
    public String toReply(long replyId) throws IOException {
        final HttpUrl httpUrl = HttpUrl.parse(ngaDomain.domain + NgaPhpApi.read.path + "?to=1&pid=" + replyId);
        final Request request = getRequest(httpUrl, null);
        try (Response response = this.client.newCall(request).execute()) {
            if (response.code() == 302) {
                return response.header("Location");
            }
        }
        return null;
    }

    /**
     * 请求一个回复Id的具体地址,使用to参数获取重定向地址
     *
     * @param replyId 回复id
     * @return 具体地址
     */
    public void toReply(long replyId, AbstractRedirectCallback callback) {
        final HttpUrl httpUrl = HttpUrl.parse(ngaDomain.domain + NgaPhpApi.read.path + "?to=1&pid=" + replyId);
        final Request request = getRequest(httpUrl, null);
        this.client.newCall(request).enqueue(callback);
    }

    /**
     * 生成url
     *
     * @param phpApi     phpApi
     * @param queryParam 地址栏查询参数
     * @param json       是否请求json格式数据
     * @return httpUrl
     */
    private HttpUrl getHttpUrl(NgaPhpApi phpApi, Object queryParam, boolean json) {
        final HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(ngaDomain.domain + phpApi.path))
                .newBuilder()
                .addQueryParameter("__inchst", "UTF8");
        if (json) {
            builder.addQueryParameter("__output", "8");
        } else {
            builder.addQueryParameter("noBBCode", null);
        }

        if (queryParam != null) {
            JacksonUtils.jsonToMap(queryParam).forEach((k, v) -> {
                if (v instanceof Collection<?>) {
                    //如果value 是集合，添加多个同名参数
                    ((Collection<?>) v).forEach(i -> builder.addQueryParameter(k, String.valueOf(i)));
                } else if (!ObjectUtils.isEmpty(v)) {
                    //常规写入
                    builder.addQueryParameter(k, v.toString());
                }
            });
        }
        return builder.build();
    }

    @NotNull
    private Request getRequest(HttpUrl httpUrl, RequestBody body) {
        final Request.Builder builder = new Request.Builder()
                .url(httpUrl)
                .header("cookie", this.cookie)
                .header("host", this.ngaDomain.domain.replace("https://",""))
                .header("Referer", this.ngaDomain.domain)
                .header("User-Agent", UA);
        if (body != null) {
            return builder.post(body).build();
        }
        return builder.get().build();
    }

    /**
     * 判断cookie是否合法
     *
     * @param cookie cookie
     * @return 是否合法
     */
    public static boolean isCookieLegal(String cookie) {
        return parseUserId(cookie) != null && parseUsername(cookie) != null && parseCid(cookie) != null;
    }


    public static String parseUserId(String cookie) {
        return parseCookie(cookie, UID_PATTERN, UID_PATTERN_2);
    }

    public static String parseUsername(String cookie) {
        return parseCookie(cookie, USERNAME_PATTERN, USERNAME_PATTERN_2);
    }

    public static String parseCid(String cookie) {
        return parseCookie(cookie, CID_PATTERN, CID_PATTERN_2);
    }


    /**
     * 尝试用不同的正则解析cookie中的数据
     *
     * @param cookie   cookie
     * @param patterns 正则表达式
     * @return 数据
     */
    private static String parseCookie(String cookie, Pattern... patterns) {
        for (Pattern pattern : patterns) {
            final Matcher matcher = pattern.matcher(cookie);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    public static String decodeUsername(String s){
        try {
            return URLDecoder.decode(s, "GB18030");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
