package com.gin.nga.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gin.nga.exception.NgaClientException;
import com.gin.nga.exception.NgaServerException;
import com.gin.nga.interfaze.ResourceParser;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 10:23
 */
public  abstract class ResourceCallback<T> extends AbstractCallback<T> {
    @Setter
    ResourceParser<T> parser;
    @Override
    public T parse(String resString) throws JsonProcessingException, NgaServerException, NgaClientException {
        return parser.parse(resString,this.eClass);
    }
}
