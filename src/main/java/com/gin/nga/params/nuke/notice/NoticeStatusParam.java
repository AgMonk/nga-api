package com.gin.nga.params.nuke.notice;

import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * 提醒信息状态
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 16:54
 */
@Getter
public class NoticeStatusParam extends NukeBaseParam {
    public NoticeStatusParam() {
        super("noti", "if");
    }
}
