package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.body.nuke.MissionCheckBody;
import com.gin.nga.response.field.MissionInfo;
import org.springframework.util.ObjectUtils;

import java.util.Iterator;
import java.util.List;

/**
 * '
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 11:18
 */
public class MissionCheckBodyDeserializer extends AbstractSingleListDeserializer<MissionCheckBody, MissionInfo> {
    private static String getFirstChild(TreeNode node) {
        final Iterator<String> iterator = node.fieldNames();
        return iterator.hasNext() ? node.get(iterator.next()).toString().trim() : "";
    }

    @Override
    public MissionCheckBody buildResult(List<MissionInfo> list) {
        return new MissionCheckBody();
    }

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public void handleItem(MissionCheckBody result, List<MissionInfo> list, String fieldName, TreeNode child) throws JsonProcessingException {
        final String s = getFirstChild(child);
        if (ObjectUtils.isEmpty(s)) {
            return;
        }
        if ("0".equals(fieldName)) {
            result.setCompleted(false);
            result.setMission(NgaRes.MAPPER.readValue(s, MissionInfo.class));
        }
        if ("1".equals(fieldName)) {
            result.setCompleted(true);
            result.setMission(NgaRes.MAPPER.readValue(s, MissionInfo.class));
        }
        if ("2".equals(fieldName)) {
            result.setMessage(s);
        }
        if ("3".equals(fieldName)) {
            result.setMoreMessage(s);
        }
    }
}
