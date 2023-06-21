package com.gin.nga.response.field;

import com.gin.jackson.utils.ObjectUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 屏蔽数据
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/8 10:57
 */
@Getter
@Setter
@NoArgsConstructor
public class BlockData {
    private static final String REGEX = "\n";
    private static final String SPACE = " ";
    /**
     * 屏蔽的用户，允许的格式： 用户名 或 用户id 或 "用户id/用户名"
     */
    List<String> users;
    /**
     * 屏蔽的关键字
     */
    List<String> keywords;

    public BlockData(String data) {
        if (ObjectUtils.isEmpty(data)) {
            return;
        }
        final String[] split = data.split(REGEX);
        this.keywords = Arrays.stream(split[1].split(SPACE)).filter(obj -> !ObjectUtils.isEmpty(obj)).collect(Collectors.toList());
        this.users = Arrays.stream(split[2].split(SPACE)).filter(obj -> !ObjectUtils.isEmpty(obj)).collect(Collectors.toList());
    }

    /**
     * 序列化为请求参数字符串
     * @return 请求参数
     */
    public String toQueryString(){
        StringBuilder sb = new StringBuilder("1").append(REGEX);
        if (!ObjectUtils.isEmpty(keywords)){
            keywords.forEach(k->sb.append(k).append(SPACE));
        }
        sb.append(REGEX);
        if (!ObjectUtils.isEmpty(users)){
            users.forEach(k->sb.append(k).append(SPACE));
        }
        return sb.toString();
    }
}
