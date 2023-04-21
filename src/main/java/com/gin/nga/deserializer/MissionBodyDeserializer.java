package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.body.nuke.MissionBody;
import com.gin.nga.response.field.mission.MissionInfo;

import java.util.List;


/**
 * 任务列表反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 09:44
 */
public class MissionBodyDeserializer extends AbstractSingleListDeserializer<MissionBody,MissionInfo> {
    @Override
    public void handleItem(MissionBody result, List<MissionInfo> list, String fieldName, TreeNode child) throws JsonProcessingException {
        list.add(NgaRes.MAPPER.readValue(child.toString(), MissionInfo.class));
    }

    @Override
    public MissionBody buildResult(List<MissionInfo> list) {
        final MissionBody body = new MissionBody();
        body.setData(list);
        return body;
    }
}
    