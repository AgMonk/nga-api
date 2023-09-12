package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.response.vote.BetData;
import com.gin.nga.response.vote.BaseVoteData;
import com.gin.nga.response.vote.VoteData;

import java.io.IOException;


/**
 * 投票数据反序列化
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/1 10:02
 */
public class VoteDataDeserializer extends JsonDeserializer<BaseVoteData> {

    public static BaseVoteData parse(String s) {
        if (ObjectUtils.isEmpty(s)){
            return null;
        }
        //        是否为菠菜
        final boolean isBet = s.contains("type~1");
        return isBet ? new BetData(s) : new VoteData(s);
    }

    @Override
    public BaseVoteData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return parse(jsonParser.getValueAsString());
    }
}
    