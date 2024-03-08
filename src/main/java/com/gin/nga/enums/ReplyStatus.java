package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gin.nga.utils.BitUtils;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题和回复的状态  <a href="https://img4.nga.178.com/common_res/js_postfunc_v2.js">参考</a>
 *
 * <a href="https://img4.nga.178.com/common_res/js_commonui.js">参考2</a>
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/15 17:31
 */
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ReplyStatus {

    /**
     * 评论:这是一条评论
     */
    IS_COMMENT(0, "评论", "这是一条评论"),
    /**
     * 隐藏:被隐藏
     */
    HIDDEN(1, "隐藏", "被隐藏"),
    /**
     * 有评论:有评论
     */
    HAS_COMMENT(2, "有评论", "有评论"),
    /**
     * 延时:是否有延时操作
     */
    DELAY_ACTION(3, "延时", "是否有延时操作"),
    /**
     * 更多:更多用户信息
     */
    EXTRA_USER_INFO(4, "更多", "更多用户信息"),
    /**
     * 被标记:是否被标记了
     */
    LOG(5, "被标记", "是否被标记了"),
    /**
     * 不再提示:不再提醒回复
     */
    NO_HINT(6, "不再提示", "不再提醒回复"),
    /**
     * 编辑:编辑时间不受限制
     */
    FREE_EDIT(7, "编辑", "编辑时间不受限制"),
    /**
     * 自回:主题只能作者自己回复
     */
    SELF_REPLY(8, "自回", "主题只能作者自己回复"),
    /**
     * 审核中:..
     */
    VERIFYING(9, "审核中", "正在人工审核.."),
    /**
     * 锁定:不允许编辑和回复
     */
    LOCKED(10, "锁定", "不允许编辑和回复"),
    /**
     * 处罚标记:用户被处罚
     */
    HIDE_TITLE(11, "处罚", "用户被处罚"),
    /**
     * 翻译:有可以自动翻译的内容
     */
    HAS_AUTO_TRANSLATE(12, "翻译", "有可以自动翻译的内容"),
    /**
     * 附件:有上传文件
     */
    HAS_UPLOAD(13, "附件", "有上传文件"),
    /**
     * 合集:这是一个合集入口
     */
    IS_SET(15, "合集", "这是一个合集入口"),
    /**
     * 合集主题:这是一个合集内主题
     */
    IS_SET_THREAD(16, "合集主题", "这是一个合集内主题"),
    /**
     * 合并版面:是否不在合并版面中显示
     */
    UNION_HIDDEN(17, "合并版面", "是否不在合并版面中显示"),
    /**
     * 未知状态:未知状态
     */
    UNKNOWN_25(25, "未知25", "未知状态"),
    /**
     * 审核不通过:且被判定为不通过
     */
    NOT_VERIFIED(26, "审核不通过", "经过了人工审核，且被判定为不通过"),
    /**
     * 未知:未知状态
     */
    UNKNOWN_27(27, "未知27", "未知状态"),
    ;
    @JsonIgnore
    public final int position;
    public final String name;
    public final String description;
    /**
     * 从 type 字段解析状态列表
     * @param type type字段
     * @return 状态列表
     */
    public static List<ReplyStatus> parse(int type) {
        if (type==0) {
            return null;
        }
        String s = BitUtils.parse(type);
        List<ReplyStatus> list = new ArrayList<>();
        for (ReplyStatus i : values()) {
            if (s.length() > i.position && s.charAt(i.position) == '1') {
                list.add(i);
            }
        }
        return list;
    }
}
