package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.jackson.utils.ObjectUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用户头像链接反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 14:46
 */
public class UserAvatarDeserializer extends JsonDeserializer<List<String>> {
    public static final String PREFIX_178 = "https://img.nga.178.com/avatars/";

    public static final String PREFIX_CN = "https://img.nga.cn/avatars/";

    @Override
    public List<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String url = jsonParser.getValueAsString();
        if (ObjectUtils.isEmpty(url)) {
            return null;
        }
        url = url.replace("http:", "https:");
        //noinspection CharsetObjectCanBeUsed
        url = URLDecoder.decode(url, "utf-8");
        if (url.startsWith(PREFIX_178) || url.startsWith(PREFIX_CN)) {
            //常规格式，进行解析
            final ArrayList<String> res = new ArrayList<>();
            final String[] split = url.split("\\|\\.a");
            // 第一段地址（完整）
            final String first = split[0];
            res.add(first);
            final String prefix = first.substring(0, first.lastIndexOf("/"));
            for (int i = 1; i < split.length; i++) {
                res.add(prefix + split[i]);
            }
            return res;

        } else {
            // 无法解析，直接返回
            return new ArrayList<>(Collections.singletonList(url));
        }
    }


}
