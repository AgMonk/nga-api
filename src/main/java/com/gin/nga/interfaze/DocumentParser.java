package com.gin.nga.interfaze;

import org.jsoup.nodes.Document;

/**
 * 网页数据解析器，
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:04
 */
public interface DocumentParser<T> {
    /**
     * 将Document解析为指定类型对象的方法
     * @param document 网页数据
     * @return T
     */
    T parse(Document document);
}
