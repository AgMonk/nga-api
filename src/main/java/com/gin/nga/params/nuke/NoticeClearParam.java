package com.gin.nga.params.nuke;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 13:47
 */
public class NoticeClearParam extends NukeBaseParam {
    final int raw =3;

    public NoticeClearParam() {
        this.lib="noti";
        this.act= "del";
    }
}
