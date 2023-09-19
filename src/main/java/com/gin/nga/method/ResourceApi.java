package com.gin.nga.method;

import com.gin.nga.call.NgaResourceCall;
import com.gin.nga.response.CommonUiData;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.emote.BbsCodeBody;
import com.gin.nga.response.forum.ForumGroupBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 静态资源API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 10:10
 */
public class ResourceApi {
    /**
     * 首页版面信息地址
     */
    public static final String INDEX_FORUMS = "https://img4.nga.178.com/proxy/cache_attach/bbs_index_data.js";
    public static final String CORE = "https://img4.nga.178.com/common_res/js_bbscode_core.js";
    public static final String COMMONUI = "https://img4.nga.178.com/common_res/js_commonui.js";
    /**
     * 请求UI相关数据
     * @return UI数据
     */
    public static NgaResourceCall<CommonUiData> commonUi(OkHttpClient client) {
        final Request request = new Request.Builder().url(COMMONUI).build();
        return new NgaResourceCall<>(client.newCall(request),CommonUiData.class,(res,clazz)-> new CommonUiData(res));
    }
    /**
     * 官方表情包、字体、字体大小、字体颜色
     * @return com.gin.nga.call.NgaResourceCall<com.gin.nga.response.emotion.EmoteBody>
     * @since 2023/5/8 14:37
     */
    public static NgaResourceCall<BbsCodeBody> bbsCode(OkHttpClient client) {
        final Request request = new Request.Builder().url(CORE).build();
        return new NgaResourceCall<>(client.newCall(request), BbsCodeBody.class, (res, clazz) -> new BbsCodeBody(res));
    }
    /**
     * 首页版面入口
     * @return com.gin.nga.call.NgaResourceCall<com.gin.nga.response.forum.ForumGroupRes>
     * @since 2023/5/8 11:48
     */
    public static NgaResourceCall<ForumGroupBody> indexForums(OkHttpClient client) {
        final Request request = new Request.Builder().url(INDEX_FORUMS).build();
        return new NgaResourceCall<>(client.newCall(request), ForumGroupBody.class, (content, valueType) ->
                NgaRes.MAPPER.readValue(content.replace("window.script_muti_get_var_store=", ""), valueType));
    }

}
