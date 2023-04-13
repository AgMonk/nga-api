package com.gin.nga.test;

import com.gin.nga.api.NgaReadApi;
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
    private final long tid = 26639977L;
//    private final long tid = 25968165L;
    private final int page = 1;
//    private final int page = 3;
    private final long pid = 683765377L;

    public void test(){
        testReadTopic();
//        testReadReply();
    }

    private void testReadReply() {
        api.readReply(pid).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ReadBody body) {
                Test.writeTestRes(body, String.format("read-%d.json", pid));
            }
        });
    }

    private void testReadTopic() {
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
