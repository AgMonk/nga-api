package com.gin.nga.method;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gin.nga.call.NgaResourceCall;
import com.gin.nga.client.NgaClient;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.emotion.EmoteBody;
import com.gin.nga.response.emotion.EmoteGroup;
import com.gin.nga.response.forum.ForumGroupBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 静态资源API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 10:10
 */
public class ResourceApi {
    private static final OkHttpClient CLIENT = NgaClient.getOkHttpClient();
    /**
     * 首页版面信息地址
     */
    private static final String INDEX_FORUMS = "https://img4.nga.178.com/proxy/cache_attach/bbs_index_data.js";
    private static final String CORE = "https://img4.nga.178.com/common_res/js_bbscode_core.js";
    /**
     * 首页版面入口
     * @return com.gin.nga.call.NgaResourceCall<com.gin.nga.response.forum.ForumGroupRes>
     * @author bx002
     * @since 2023/5/8 11:48
     */
    public static NgaResourceCall<ForumGroupBody> indexForums(){
        final Request request = new Request.Builder().url(INDEX_FORUMS).build();
        return  new NgaResourceCall<>(CLIENT.newCall(request), ForumGroupBody.class, (content, valueType) -> {
            return NgaRes.MAPPER.readValue(content.replace("window.script_muti_get_var_store=", ""), valueType);
        });
    }

    /**
     * 官方表情包
     * @return com.gin.nga.call.NgaResourceCall<com.gin.nga.response.emotion.EmoteBody>
     * @author bx002
     * @since 2023/5/8 14:37
     */
    public static NgaResourceCall<EmoteBody> emotion(){
        final Request request = new Request.Builder().url(CORE).build();
        return new NgaResourceCall<>(CLIENT.newCall(request), EmoteBody.class, (res, clazz) -> {
            StringBuilder builder = new StringBuilder();
            boolean enable = false;
            // 逐行处理
            for (String line : res.split("\n")) {
                if ("}//s".equals(line)){
                    // 结束
                    builder.append("}");
                    break;
                }
                if ("ubbcode.smiles = {".equals(line)){
                    // 开始
                    enable = true;
                    builder.append("{");
                    continue;
                }
                if (enable){
                    if (line.startsWith("//")){
                        // 注释行，直接跳过
                        continue;
                    }
                    final String s = line
                            .replaceAll("//.+", "")
                            .replaceAll("^(\\w+?):","\"$1\":")
                            .replace("'", "\"");
                    if (!ObjectUtils.isEmpty(s)) {
                        builder.append(s);
                    }
                }

            }

            LinkedHashMap<String,LinkedHashMap<String,String>> map = NgaRes.MAPPER.readValue(builder.toString(), new TypeReference<>() {
            });
            // 0组已废弃 无法渲染
            map.remove("0");

            final List<EmoteGroup> data = map.entrySet().stream()
                    .map(entry -> new EmoteGroup(entry.getKey(), entry.getValue()))
                    .toList();

            return new EmoteBody(data);
        });
    }
}
