package com.gin.nga.test;

import com.gin.nga.api.NgaReadApi;
import com.gin.nga.callback.DocCallback;
import com.gin.nga.callback.JsonCallback;
import com.gin.nga.params.read.ReadTopicParam;
import com.gin.nga.response.body.ReadBody;
import lombok.RequiredArgsConstructor;

/**
 * read.php 接口测试
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 13:36
 */
@RequiredArgsConstructor
public class ReadTest {
    private final NgaReadApi api;

    public void test() {
//        testReadTopic();
        testDocument();
//        testReadReply();
    }

    /**
     * 测试网页解析
     */
    private void testDocument() {
//        testTopicDocument(13055900, 1);
//        testTopicDocument(35937951, 1);
        testTopic(35938322, 1);
//        testTopicDocument(22885868, 1);
        testTopicDocument(35938322, 1);
//        testTopicDocument(26639977, 1);
//        testTopicDocument(28463884, 1);
//        testTopicDocument(22885868, 1);
    }

    private void testTopicDocument(long tid, int page) {
        final ReadTopicParam param = new ReadTopicParam();
        param.setPage(page);
        param.setTopicId(tid);
        api.readTopicDoc(param).async(new DocCallback<>() {
            @Override
            public void onSuccess(ReadBody body) {
                Test.writeTestRes(body, String.format("read-doc-%d-%d.json", tid, page));
            }
        });
    }

    private void testReadReply() {
        long pid = 683765377L;
        api.readReply(pid).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ReadBody body) {
                Test.writeTestRes(body, String.format("read-%d.json", pid));
            }
        });
    }

    private void testReadTopic() {
        testAlter();
        // 测试匿名
        testTopic(25968165, 3);
        testTopic(35937951, 1);
    }

    /**
     * 测试修改
     */
    private void testAlter() {
        testTopic(13055900, 1);
        testTopic(26639977, 1);
        testTopic(28463884, 1);
        testTopic(22885868, 1);
    }

    private void testTopic(long tid, int page) {
        final ReadTopicParam param = new ReadTopicParam();
        param.setPage(page);
        param.setTopicId(tid);
        api.readTopic(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ReadBody body) {
                Test.writeTestRes(body, String.format("read-%d-%d.json", tid, page));
            }
        });
    }
}   
