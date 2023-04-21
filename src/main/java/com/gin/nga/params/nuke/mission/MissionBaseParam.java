package com.gin.nga.params.nuke.mission;

import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 任务基础参数 <a href="https://bbs.nga.cn/read.php?pid=129353635&opt=128">文档</a>
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 09:25
 */
@Getter
@Setter
public class MissionBaseParam extends NukeBaseParam {

    public MissionBaseParam(String act) {
        super("mission", act);
    }
}
