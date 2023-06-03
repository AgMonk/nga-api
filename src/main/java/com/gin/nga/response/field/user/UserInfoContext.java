package com.gin.nga.response.field.user;

import com.gin.nga.response.field.LabelValue;
import com.gin.nga.response.field.Medal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 由UserContext返回的用户信息
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/3 09:12
 */
@Getter
@Setter
@NoArgsConstructor
public class UserInfoContext extends UserInfoRead{
    /**
     * 声望等级+声望值
     */
    LabelValue reputation;
    /**
     * 随机头像
     */
    String avatar;
    /**
     * 徽章信息
     */
    List<Medal> medals;
    /**
     * 用户组
     */
    UserGroup group;
    /**
     * 复制父类信息
     * @param userInfoRead 由read接口返回的用户信息
     */
    public UserInfoContext(UserInfoRead userInfoRead) {
        BeanUtils.copyProperties(userInfoRead,this);
    }
}
