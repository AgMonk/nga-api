package com.gin.nga.params.nuke.notice;

import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 13:47
 */
@Getter
public class NoticeClearParam extends NukeBaseParam {
    final int raw = 3;

    public NoticeClearParam() {
super("noti","del");
    }
}
