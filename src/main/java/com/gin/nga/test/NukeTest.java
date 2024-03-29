package com.gin.nga.test;

import lombok.RequiredArgsConstructor;

/**
 * nuke.php 的测试
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 11:20
 */
@RequiredArgsConstructor
public class NukeTest {
    /*private final NgaNukeApi ngaNukeApi;
    private final NgaForumApi ngaForumApi;
    private final NgaNoticeApi ngaNoticeApi;

    public NukeTest(NgaClient ngaClient) {
        this.ngaNukeApi = new NgaNukeApi(ngaClient);
        this.ngaForumApi = new NgaForumApi(ngaClient);
        this.ngaNoticeApi = new NgaNoticeApi(ngaClient);
    }

    public void testBlockForum(long forumId) throws IOException {
        final BlockSubForum blockSubForumBody = ngaForumApi.getBlockSubForum(forumId).sync().get(0);
        final Long id = blockSubForumBody.getBlockTopicId().get(1);
        ngaForumApi.delBlockSubForum(forumId, id).sync();
        ngaForumApi.getBlockSubForum(forumId).sync().get(0);
        ngaForumApi.addBlockSubForum(forumId, id).sync();
        ngaForumApi.getBlockSubForum(forumId).sync().get(0);

    }

    public void testFavorCol() throws IOException {
        JacksonUtils.printPretty(ngaForumApi.editFavorForum(FavorAction.add, FavorType.col, 28803280).sync());
        ngaForumApi.getFavorForum().sync().get(0);
        JacksonUtils.printPretty(ngaForumApi.editFavorForum(FavorAction.del, FavorType.col, 28803280).sync());
        ngaForumApi.getFavorForum().sync().get(0);
    }

    public void testFavorForum() throws IOException {
        JacksonUtils.printPretty(ngaForumApi.editFavorForum(FavorAction.add, FavorType.forum, 428).sync());
        ngaForumApi.getFavorForum().sync().get(0);
        JacksonUtils.printPretty(ngaForumApi.editFavorForum(FavorAction.del, FavorType.forum, 428).sync());
        ngaForumApi.getFavorForum().sync().get(0);
    }

    public void testGetUserInfo() {
        List<Long> uidList = Arrays.asList(
                39841854L
                , 35159831L
                , 24984254L
                ,993945L
                ,195362L
                ,38148967L
        );

        for (Long uid : uidList) {
            ngaNukeApi.getUserInfo(uid).async(new JsonCallback<>() {
                @Override
                public void onSuccess(UserInfoBody body) {
                    Test.writeTestRes(body.getData(), String.format("user-info-%d.json", uid));
                }
            });
        }

        List<String> nameList = Arrays.asList(
                "左牵黄右擒苍"
                 , "血牙酱"
        );
        for (String name : nameList) {
            api.getUserInfo(name).async(new JsonCallback<>() {
                @Override
                public void onSuccess( UserInfoBody.Res body) {
                    Test.writeTestRes(body.get(0), String.format("user-info-%s.json", name));
                }
            });
        }
    }

    public void testNotice() throws IOException {
        JacksonUtils.printPretty(ngaNoticeApi.clear().sync());
    }

    public void testRecommend() throws IOException {
    }
*/}
