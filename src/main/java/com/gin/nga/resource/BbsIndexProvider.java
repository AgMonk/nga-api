package com.gin.nga.resource;


import com.fasterxml.jackson.core.type.TypeReference;
import com.gin.common.utils.FileIoUtils;
import com.gin.jackson.utils.JacksonUtils;
import com.gin.nga.response.forum.ForumGroup;
import com.gin.nga.response.forum.ForumGroupBody;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

/**
 * 首页信息资源提供者
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/9/19 15:28
 **/
public class BbsIndexProvider extends ResourceProvider<List<ForumGroup>> {
    public static final String URL = "https://img4.nga.178.com/proxy/cache_attach/bbs_index_data.js";

    public BbsIndexProvider(@NonNull OkHttpClient client, @NonNull File cacheFile, long expires) {
        super(URL, client, cacheFile, expires);
    }


    @Override
    protected List<ForumGroup> readCache(File cacheFile) throws IOException {
        return FileIoUtils.readObj(cacheFile, new TypeReference<List<ForumGroup>>() {
        });
    }

    @Override
    protected List<ForumGroup> convert(@NonNull Response response) throws IOException {
        if (response.code() == 200) {
            try (ResponseBody responseBody = response.body()) {
                if (responseBody != null) {
                    final String s = new String(responseBody.bytes(), Charset.forName("GB18030"))
                            .replace("window.script_muti_get_var_store=", "");
                    return JacksonUtils.MAPPER.readValue(s, ForumGroupBody.class).getGroups();
                }
            }
        }
        throw new IOException(String.format(Locale.CHINA, "code: %d 响应数据为空", response.code()));
    }
}
