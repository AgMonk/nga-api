package com.gin.nga.response.field;

import com.gin.nga.response.alterlog.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 修改记录, 包括编辑、加分、处罚、撤销处罚 ,
 * 测试用例: 13055900 26639977 28463884 22885868
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 17:28
 */
@Getter
@Setter
@NoArgsConstructor
public class AlterInfo {
    public static final Pattern PATTERN = Pattern.compile("\\[(.+?)]");

    /**
     * 最后编辑时间
     */
    LastEdit lastEdit;
    /**
     * 操作日志
     */
    List<ReputationLog> logs = new ArrayList<>();

    public AlterInfo(String s) {
        if (ObjectUtils.isEmpty(s)) {
            return;
        }
        final Matcher matcher = PATTERN.matcher(s);
        while (matcher.find()) {
            final String group = matcher.group(1);
            if (group.startsWith("E")) {
                this.lastEdit = new LastEdit(group);
            } else if (group.startsWith("A")) {
                this.logs.add(new RewardLog(group));
            }else if (group.startsWith("L")) {
                this.logs.add(new PunishmentLog(group));
            }else if (group.startsWith("U")) {
                this.logs.add(new CanceledLog(group));
            }
        }
    }
}
