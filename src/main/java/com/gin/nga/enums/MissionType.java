package com.gin.nga.enums;

import com.gin.nga.utils.BitUtils;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务类型
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 12:54
 */
@RequiredArgsConstructor
public enum MissionType {

    /**
     * 需要计数的任务
     */
    COUNTER(0,"需要计数的任务"),
    /**
     * 可以重复完成的任务
     */
    REPEAT(1,"可以重复完成的任务"),
    /**
     * 必须用户级别激活级别1或更高
     */
    ACTIVE(2,"必须用户级别激活级别1或更高"),
    /**
     * 必须用户激活级别0
     */
    NO_ACTIVE(3,"必须用户激活级别0"),
    /**
     * 必须是认证的客户端
     */
    CLIENT(4,"必须是认证的客户端"),
    /**
     * 不公开任务条件
     */
    HIDE_COND(5,"不公开任务条件"),
    /**
     * 客户端在获取到available任务时应自动检查__act_check_mission任务是否完成
     */
    AUTO_CHECK(6,"客户端在获取到available任务时应自动检查__act_check_mission任务是否完成"),
    /**
     * 客户端在获取到available任务后 应由用户自己操作检查任务是否完成
     */
    MANUAL_CHECK(7,"客户端在获取到available任务后 应由用户自己操作检查任务是否完成"),
    UNKNOWN(8,"未知"),
    ;

    public final int position;
    public final String message;

    /**
     * 从 type 字段解析状态列表
     * @param type type字段
     * @return 状态列表
     */
    public static List<MissionType> parse(int type) {
        if (type == 0) {
            return null;
        }
        String s = BitUtils.parse(type);
        List<MissionType> list = new ArrayList<>();
        for (MissionType i : values()) {
            if (s.length() > i.position && s.charAt(i.position) == '1') {
                list.add(i);
            }
        }
        return list;
    }
}
