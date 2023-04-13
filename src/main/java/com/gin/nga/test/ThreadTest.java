package com.gin.nga.test;

import com.gin.nga.api.NgaThreadApi;
import com.gin.nga.callback.JsonCallback;
import com.gin.nga.enums.OrderByParam;
import com.gin.nga.params.thread.*;
import com.gin.nga.response.body.ThreadBody;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static com.gin.nga.enums.BoolParam.no;
import static com.gin.nga.enums.BoolParam.yes;

/**
 * thread.php 接口测试
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 12:04
 */
@RequiredArgsConstructor
@Setter
public class ThreadTest {
    private final NgaThreadApi api;
    /**
     * 合集id
     */
    private final long colTid = 29111018L;
    /**
     * 版面id
     */
    private final long forumId = 428;
    private final long forumId2 = -547859L;

    /**
     * 搜索关键字
     */
    private final String keyword = "体力";
    private final long userId = 60371186L;

    private boolean testColList = false;
    private boolean testColSearch = false;
    private boolean testFavor = false;
    private boolean testForumList = false;
    private boolean testForumSearch = false;
    private boolean testUserSearch = false;

    /**
     * 执行测试
     */
    public void test() {
        if (testColList) {
            colListTest();
        }
        if (testColSearch) {
            colSearchTest();
        }
        if (testFavor) {
            favorTest();
        }
        if (testForumList) {
            forumListTest();
        }
        if (testForumSearch) {
            forumSearchTest();
        }
        if (testUserSearch) {
            userSearchTest();
        }
    }

    private void colListTest() {
        final ColListParam param = new ColListParam();
        param.setColTid(colTid);
        param.setOrderBy(OrderByParam.created);
        api.colList(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                Test.writeTestRes(body, "colList-created.json");
            }
        });
        param.setPage(30);
        param.setOrderBy(OrderByParam.replied);
        api.colList(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                Test.writeTestRes(body, "colList-replied.json");
            }
        });
    }

    private void colSearchTest() {
        final ColSearchParam param = new ColSearchParam();
        param.setColTid(colTid);
        param.setKeyword(keyword);
        api.colSearch(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                Test.writeTestRes(body, "colSearch-normal.json");
            }
        });
        param.setRecommendOnly(yes);
        param.setSearchContent(yes);
        api.colSearch(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                Test.writeTestRes(body, "colSearch-rec-content.json");
            }
        });
    }

    private void favorTest() {
        final FavorParam param = new FavorParam();
        param.setPage(2);
        api.favor(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                Test.writeTestRes(body, "favor.json");
            }
        });
    }

    /**
     * 版面浏览测试
     */
    private void forumListTest() {
        final ForumListParam param = new ForumListParam();
        param.setForumId(forumId);
        param.setOrderBy(OrderByParam.created);
        api.forumList(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                Test.writeTestRes(body, "forumList-created.json");

            }
        });
        param.setOrderBy(OrderByParam.replied);
        api.forumList(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                Test.writeTestRes(body, "forumList-replied.json");

            }
        });
    }

    /**
     * 版面搜索测试
     */
    private void forumSearchTest() {
        final ForumSearchParam param = new ForumSearchParam();
        param.setForumId(forumId);
        param.setKeyword(keyword);

        api.forumSearch(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                Test.writeTestRes(body, "forumSearch-normal.json");

            }
        });
        param.setRecommendOnly(yes);
        param.setSearchContent(yes);
        api.forumSearch(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                Test.writeTestRes(body, "forumSearch-rec-content.json");

            }
        });
    }

    /**
     * 用户发言测试
     */
    private void userSearchTest() {
        final UserSearchParam param = new UserSearchParam();
        param.setAuthorId(userId);
        param.setForumId(forumId2);
        param.setRecommendOnly(yes);
        api.userSearch(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                Test.writeTestRes(body, "userSearch-rec.json");
            }
        });

        param.setRecommendOnly(no);
        param.setSearchReply(1);
        api.userSearch(param).async(new JsonCallback<>() {
            @Override
            public void onSuccess(ThreadBody body) {
                Test.writeTestRes(body, "userSearch-reply.json");
            }
        });
    }
}
