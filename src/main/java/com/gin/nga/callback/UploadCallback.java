package com.gin.nga.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gin.nga.exception.NgaException;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.body.UploadBody;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 16:22
 */
public abstract class UploadCallback extends AbstractCallback<UploadBody> {
    @Override
    public UploadBody parse(String resString) throws JsonProcessingException, NgaException {
        final UploadBody body = NgaRes.MAPPER.readValue(resString, UploadBody.class);
        handleException(body);
        return body;
    }

    public static void handleException(UploadBody body) throws NgaException {
        final Integer errorCode = body.getErrorCode();
        final String error = body.getError();
        if (errorCode!=null) {
            switch (errorCode) {
                case 3:
                    throw new NgaException(errorCode,null,"上传令牌已过期, 请重新获取");
                case 6:
                    throw new NgaException(errorCode,null,"文件类型错误");
                default:
                    throw new NgaException(errorCode,null,error);
            }
        }
    }
}
