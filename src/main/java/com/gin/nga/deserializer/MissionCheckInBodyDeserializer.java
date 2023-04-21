package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.body.nuke.MissionCheckBody;
import com.gin.nga.response.field.mission.MissionInfo;
import com.gin.nga.utils.TreeNodeUtils;

import java.io.IOException;
import java.util.Iterator;

/**
 * '
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 11:18
 */
public class MissionCheckInBodyDeserializer extends JsonDeserializer<MissionCheckBody> {
    private static MissionInfo findMissionInfo(TreeNode root) throws JsonProcessingException {
        final Iterator<String> iterator = root.fieldNames();
        while (iterator.hasNext()) {
            final String next = iterator.next();
            final TreeNode node = TreeNodeUtils.findFirstChild(root.get(next));
            if (node != null) {
                final String nodeString = node.toString();
                if (nodeString.startsWith("{")) {
                    return NgaRes.MAPPER.readValue(nodeString, MissionInfo.class);
                }
            }
        }
        return null;
    }

    @Override
    public MissionCheckBody deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final MissionCheckBody result = new MissionCheckBody();
        final TreeNode root = p.getCodec().readTree(p);

        // 是否完成
        {
            final TreeNode node0 = root.get("0");
            final TreeNode child = TreeNodeUtils.findFirstChild(node0);
            if (child != null) {
                result.setCompleted("7".equals(child.toString()));
            }
        }
        // 任务信息
        {
            final TreeNode node1 = root.get("1");
            if (node1 != null) {
                // 任务信息
                result.setMissionInfo(findMissionInfo(node1));

                // 奖励消息
                final TreeNode node12 = node1.get("2");
                if (node12 != null) {
                    final TreeNode node = TreeNodeUtils.findFirstChild(node12);
                    if (node != null) {
                        result.setMessage(node.toString());
                    }
                }

            }

        }

        return result;

    }
}
