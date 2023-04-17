package com.gin.nga.test;

import com.gin.nga.api.NgaNukeApi;
import com.gin.nga.callback.JsonCallback;
import com.gin.nga.enums.FavorAction;
import com.gin.nga.enums.FavorType;
import com.gin.nga.response.body.nuke.UserInfoBody;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

/**
 * nuke.php 的测试
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 11:20
 */
@RequiredArgsConstructor
public class NukeTest {
    private final NgaNukeApi api;

    public void testGetUserInfo() {
        List<Long> uidList = List.of(
                39841854L
                ,35159831L
//                ,993945L
//                ,195362L
//                ,38148967L
        );

        for (Long uid : uidList) {
            api.getUserInfo(uid).async(new JsonCallback<>() {
                @Override
                public void onSuccess( UserInfoBody.Res body) {
                    Test.writeTestRes(body.get(0), String.format("user-info-%d.json", uid));
                }
            });
        }

//        List<String> nameList = List.of(
//                "左牵黄右擒苍"
//                 , "血牙酱"
//        );
//        for (String name : nameList) {
//            api.getUserInfo(name).async(new JsonCallback<>() {
//                @Override
//                public void onSuccess( UserInfoBody.Res body) {
//                    Test.writeTestRes(body.get(0), String.format("user-info-%s.json", name));
//                }
//            });
//        }
    }

    public void testFavorForum() throws IOException {
        api.editFavorForum(FavorAction.add, FavorType.forum,428).sync();
        api.getFavorForum().sync().get(0);
        api.editFavorForum(FavorAction.del, FavorType.forum,428).sync();
        api.getFavorForum().sync().get(0);
    }
    public void testFavorCol() throws IOException {
        System.out.println( api.editFavorForum(FavorAction.add, FavorType.col,28803280).sync());
        api.getFavorForum().sync().get(0);
        System.out.println( api.editFavorForum(FavorAction.del, FavorType.col,28803280).sync());
        api.getFavorForum().sync().get(0);
    }
}   
