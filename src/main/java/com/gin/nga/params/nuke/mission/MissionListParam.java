package com.gin.nga.params.nuke.mission;

import lombok.Getter;
import lombok.Setter;

/**
 * 任务查询参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 09:28
 */
@Getter
@Setter
public class MissionListParam extends  MissionBaseParam{
    /**
     * 触发任务的事件ID  (目前仅发现1和2有数据返回)
     */
    final int event;
    /**
     * 任务类型bit 取值 1:需要计数器的任务/2:可以重复完成的任务
     */
    final int type;
    final int available = 1;

    public MissionListParam(int event, int type) {
        super("get");
        this.event = event;
        this.type = type;
    }
}
