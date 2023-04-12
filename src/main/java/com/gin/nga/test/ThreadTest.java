package com.gin.nga.test;

import com.gin.nga.api.NgaThreadApi;
import com.gin.nga.callback.JsonCallback;
import com.gin.nga.enums.BoolParam;
import com.gin.nga.enums.OrderByParam;
import com.gin.nga.params.thread.ColListParam;
import com.gin.nga.params.thread.ColSearchParam;
import com.gin.nga.params.thread.FavorParam;
import com.gin.nga.response.body.ThreadBody;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

/**
 * thread.php 接口测试
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 12:04
 */
@RequiredArgsConstructor
public class ThreadTest {
    private final NgaThreadApi api;
    /**
     * 合集id
     */
    private final long colTid;
    /**
     * 版面id
     */
    private final long forumId;
    /**
     * 搜索关键字
     */
    private final String keyword;

    public void colListTest() {
        final ColListParam param = new ColListParam();
        param.setColTid(colTid);
        param.setOrderBy(OrderByParam.created);
        api.colList(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                try {
                    Test.writeTestRes(body, "colList-created.json");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        param.setPage(30);
        param.setOrderBy(OrderByParam.replied);
        api.colList(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                try {
                    Test.writeTestRes(body, "colList-replied.json");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void colSearchTest() {
        final ColSearchParam param = new ColSearchParam();
        param.setColTid(colTid);
        param.setKeyword(keyword);
        api.colSearch(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                try {
                    Test.writeTestRes(body, "colSearch-normal.json");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        param.setRecommendOnly(BoolParam.yes);
        param.setSearchContent(BoolParam.yes);
        api.colSearch(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                try {
                    Test.writeTestRes(body, "colSearch-rec-content.json");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void favorTest(){
        final FavorParam param = new FavorParam();
        param.setPage(2);
        api.favor(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                try {
                    Test.writeTestRes(body, "favor.json");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}   
