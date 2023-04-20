package com.gin.nga.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gin.nga.exception.NgaClientException;
import com.gin.nga.exception.NgaServerException;
import com.gin.nga.response.NgaRes;

/**
 * Json数据的响应回调
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 15:55
 */
public abstract class JsonCallback<T> extends AbstractCallback<T> {
    /**
     * 将字符串解析为指定类型对象
     * @param resString body
     * @return 对象
     * @throws JsonProcessingException 解析错误
     */
    @Override
    public T parse(String resString) throws JsonProcessingException, NgaServerException, NgaClientException {
        final NgaRes<T> res = NgaRes.parse(resString, eClass);
        if (res.getData()==null) {
            throw new NgaClientException(400, null, res.getError());
        }
        return res.getData();
    }
}
