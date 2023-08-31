package com.gin.nga.enums;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 子版面的状态: <a href="https://img4.nga.178.com/common_res/js_commonui.js">来源</a>
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/31 13:28
 **/
@RequiredArgsConstructor
public enum SubForumStatus {
    UNION_FORUM(1, "联合版面"),
    SELECTABLE(2, "用户可选择的"),
    SELECTED_EXTRA(4, "(用户设置合并默认设置之后)已选择的"),
    SET_BY_ADMIN(8, "版主设置的"),
    IS_SET(16, "是个主题集合"),
    IS_FORUM(32, "是个到版面的镜像"),
    THIS_FORUM(64, "是当前所在的版面"),
    SELECTED(128, "用户选择的"),
    NOT_SELECTED(256, "用户未选择的"),
    SELECTED_DEFAULT(512, "默认选择的"),
    NOT_SELECTED_DEFAULT(1024, "默认未选择的"),
    ;
    public final int bit;
    public final String name;

    public static List<SubForumStatus> parseStatus(int bit){
        final ArrayList<SubForumStatus> list = new ArrayList<>();
        for (SubForumStatus status : values()) {
            if ((status.bit & bit) !=0){
                list.add(status);
            }
        }
        return list;
    }
}
