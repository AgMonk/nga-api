package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.gin.jackson.utils.JacksonUtils;
import com.gin.nga.response.field.user.UserRemarkNuke;

/**
 * 用户备注反序列化(nuke接口)
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/17 16:45
 **/
public class UserRemarkNukeDeserializer extends AbstractListDeserializer<UserRemarkNuke> {
    @Override
    public UserRemarkNuke convert(TreeNode treeNode) throws JsonProcessingException {
        return JacksonUtils.MAPPER.readValue(treeNode.toString(),UserRemarkNuke.class);
    }

//    @Override
//    public List<UserRemarkNuke> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
//        final TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
//        if (ObjectUtils.isEmpty(value) || !value.contains("{")){
//            return null;
//        }
//        LinkedHashMap<Integer,UserRemarkNuke>map =  JacksonUtils.MAPPER.readValue(jsonParser, new TypeReference<LinkedHashMap<Integer, UserRemarkNuke>>() {
//        });
//        return new ArrayList<>(map.values());
//    }
}
