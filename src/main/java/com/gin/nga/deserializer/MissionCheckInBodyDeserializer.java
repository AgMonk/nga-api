package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.body.nuke.MissionCheckBody;

import java.io.IOException;

/**
 * '
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 11:18
 */
public class MissionCheckInBodyDeserializer extends JsonDeserializer<MissionCheckBody> {
    @Override
    public MissionCheckBody deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        //todo

        return  null;

    }
}
