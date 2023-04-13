package com.gin.nga.response.field;

import com.gin.nga.utils.AnonymousUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 匿名用户
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 16:06
 */
@Getter
@Setter
public class AnonymousUser {
    /**
     * 序号
     */
    Integer index;
    /**
     * 用户名
     */
    String username;

    /**
     * 中文简短昵称
     * @return 昵称
     */
    public String getNickname() {
        return username == null ? null : AnonymousUtils.getAnonymousName(username);
    }
}
