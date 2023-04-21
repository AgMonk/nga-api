package com.gin.nga.params.nuke.mission;

import com.gin.nga.enums.MissionEvent;
import com.gin.nga.enums.MissionType;
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
    /**
     * 只返回可完成任务
     */
    final int available = 1;

    /**
     * 签到任务
     */
    public MissionListParam() {
        this(MissionEvent.SIGN_IN, MissionType.COUNTER);
    }

    public MissionListParam(MissionEvent event, MissionType type) {
        super("get");
        this.event = event.id;
        this.type = (int) Math.pow(2, type.position);
    }

}
