package com.gin.nga.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gin.nga.exception.NgaServerException;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.body.UploadBody;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 16:22
 */
public abstract class UploadCallback extends AbstractCallback<UploadBody> {
    @Override
    public UploadBody parse(String resString) throws JsonProcessingException, NgaServerException {
        return NgaRes.MAPPER.readValue(resString,UploadBody.class);
    }
}
