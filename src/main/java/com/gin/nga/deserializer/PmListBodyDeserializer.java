package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.common.utils.StrUtils;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.body.nuke.PmListBody;
import com.gin.nga.response.field.pm.PrivateMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 私信反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 13:54
 */
public class PmListBodyDeserializer extends JsonDeserializer<PmListBody> {
    @Override
    public PmListBody deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final TreeNode treeNode = p.getCodec().readTree(p);
        final TreeNode root = treeNode.get(treeNode.fieldNames().next());

        final PmListBody res = new PmListBody();
        final List<PrivateMessage> data = new ArrayList<>();
        res.setData(data);
        final Iterator<String> iterator = root.fieldNames();
        while (iterator.hasNext()) {
            final String fieldName = iterator.next();
            final TreeNode node = root.get(fieldName);
            if (StrUtils.isNumber(fieldName)) {
                // 数字 ，是私信
                data.add(NgaRes.MAPPER.readValue(node.toString(), PrivateMessage.class));
            }else{
                //noinspection EnhancedSwitchMigration
                switch (fieldName){
                    case "nextPage":res.setHasNext(Boolean.parseBoolean(node.toString())); break;
                    case "currentPage":res.setPage(Integer.parseInt(node.toString()));break;
                    case "rowsPerPage":res.setSize(Integer.parseInt(node.toString()));break;
                    default:
                        System.out.printf("未识别的字段: %s -> %s\n",fieldName,node.toString());
                }
            }
        }
        return res;
    }
}
    