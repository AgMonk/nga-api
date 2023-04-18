package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;


/**
 * nuke.php接口通用反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 14:48
 */
public abstract class NukeDeserializer<T> extends JsonDeserializer<T> {
    @Override
    public final T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return convert(p.getCodec().readTree(p).get("0"));
    }

    /**
     * 将节点转换为响应类型
     * @param treeNode 树节点
     * @return 响应
     * @throws JsonProcessingException 解析异常
     */
    public abstract T convert(TreeNode treeNode) throws JsonProcessingException;

}
    