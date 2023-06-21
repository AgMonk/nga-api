package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.gin.common.utils.StrUtils;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.body.nuke.PmListBody;
import com.gin.nga.response.field.pm.PrivateMessage;

import java.util.List;


/**
 * 私信反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 13:54
 */
public class PmListBodyDeserializer extends AbstractSingleListDeserializer<PmListBody,PrivateMessage> {
    @Override
    public void handleItem(PmListBody result, List<PrivateMessage> list, String fieldName, TreeNode child) throws JsonProcessingException {
        if (StrUtils.isNumber(fieldName)) {
            // 数字 ，是私信
            list.add(NgaRes.MAPPER.readValue(child.toString(), PrivateMessage.class));
        }else{
            switch (fieldName){
                case "nextPage":result.setHasNext(Boolean.parseBoolean(child.toString())); break;
                case "currentPage":result.setPage(Integer.parseInt(child.toString()));break;
                case "rowsPerPage":result.setSize(Integer.parseInt(child.toString()));break;
                default:
                    System.out.printf("未识别的字段: %s -> %s\n",fieldName,child.toString());
            }
        }
    }

    @Override
    public PmListBody buildResult(List<PrivateMessage> list) {
        final PmListBody res = new PmListBody();
        res.setData(list);
        return res;
    }
}
    