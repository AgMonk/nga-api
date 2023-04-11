package com.gin.nga.callback;

import com.gin.nga.interfaze.DocumentParser;
import lombok.Setter;
import org.jsoup.Jsoup;

/**
 * 网页数据响应回调
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:00
 */
public abstract class DocCallback<T> extends AbstractCallback<T> {
    @Setter
    DocumentParser<T> documentParser;

    /**
     * 将字符串解析为指定类型对象
     * @param string body
     * @return 对象
     */
    @Override
    public T parse(String string) {
        return documentParser.parse(Jsoup.parse(string));
    }
}
