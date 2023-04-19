package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.TopicKeyDeserializer;
import com.gin.nga.response.field.TopicKey;

import java.util.ArrayList;

/**
 * @author bx002
 */
@JsonDeserialize(using = TopicKeyDeserializer.class)
public class TopicKeys extends ArrayList<TopicKey> {
}