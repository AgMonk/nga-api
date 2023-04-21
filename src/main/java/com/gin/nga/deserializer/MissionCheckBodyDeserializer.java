package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.body.nuke.MissionCheckBody;
import com.gin.nga.response.field.MissionInfo;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * '
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 11:18
 */
public class MissionCheckBodyDeserializer extends AbstractSingleListDeserializer<MissionCheckBody, MissionInfo> {
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public void handleItem(MissionCheckBody result, List<MissionInfo> list, String fieldName, TreeNode child) throws JsonProcessingException {
        final String s = child.toString().trim();
        if ("0".equals(fieldName) && !ObjectUtils.isEmpty(s)) {
            result.setCompleted(false);
            result.setMission(NgaRes.MAPPER.readValue(getFirstChild(child).toString(), MissionInfo.class));
        }
        if ("1".equals(fieldName) && !ObjectUtils.isEmpty(s)) {
            result.setCompleted(true);
            result.setMission(NgaRes.MAPPER.readValue(getFirstChild(child).toString(), MissionInfo.class));
        }
        if ("2".equals(fieldName) && !ObjectUtils.isEmpty(s)) {
            result.setMessage(s);
        }
        if ("3".equals(fieldName) && !ObjectUtils.isEmpty(s)) {
            result.setMoreMessage(s);
        }
    }

    @Override
    public MissionCheckBody buildResult(List<MissionInfo> list) {
        return new MissionCheckBody();
    }

    private static TreeNode getFirstChild(TreeNode node){
        return node.get(node.fieldNames().next());
    }
}
