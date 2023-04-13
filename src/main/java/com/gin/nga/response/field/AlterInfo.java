package com.gin.nga.response.field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 修改记录, 包括编辑、处罚、撤销处罚 , 测试用例: <a href="https://bbs.nga.cn/read.php?tid=26639977">帖子</a>
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 17:28
 */
@Getter
@Setter
@NoArgsConstructor
public class AlterInfo {
    public static final Pattern PATTERN = Pattern.compile("\\[(.+)]");
    /**
     * 最后编辑时间
     */
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime lastEdit;

    public AlterInfo(String s) {
        System.out.println("s = " + s);
        if (ObjectUtils.isEmpty(s)) {
            return;
        }
        final Matcher matcher = PATTERN.matcher(s);
        while (matcher.find()) {
            final String[] split = matcher.group(1).split(" ");
            if (split[0].startsWith("E")) {
                // 编辑操作

            } else if (split[0].startsWith("L")) {

            }else if (split[0].startsWith("U")) {

            }
        }
    }
}
