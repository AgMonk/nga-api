package com.gin.nga.call;

import com.gin.nga.callback.DocCallback;
import com.gin.nga.interfaze.DocumentParser;
import okhttp3.Call;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Nga Call
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 15:14
 */
public class NgaDocCall<T> extends NgaCall<T> {
    final DocumentParser<T> documentParser;

    public NgaDocCall(Call call, Class<T> responseClass, DocumentParser<T> documentParser) {
        super(call, responseClass);
        this.documentParser = documentParser;
    }

    /**
     * 异步请求
     * @param callback 回调方法
     */
    public void async(DocCallback<T> callback){
        callback.setEClass(responseClass);
        callback.setDocumentParser(documentParser);
        this.call.enqueue(callback);
    }

    /**
     * 同步请求
     * @return {@link T}
     */
    public T sync() throws IOException {
        final String s = this.syncString();
        if (s == null) {
            return null;
        }
        if (responseClass == String.class){
            //noinspection unchecked
            return (T) s;
        }
        return documentParser.parse(Jsoup.parse(s));
    }
}
