package com.gin.nga.response.vote;


import com.gin.common.utils.StrUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 投票数据
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/1 10:01
 */
@Getter
@Setter
public class VoteData extends BaseVoteData {
    /**
     * 选项
     */
    List<VoteOption> options = new ArrayList<>();

    public VoteData(String s) {
        super(s);

        for (Map.Entry<String, String> entry : this.map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (StrUtils.isNumber(k)) {
                final VoteOption option = new VoteOption();
                final int id = Integer.parseInt(k);
                final String[] split = this.map.get("_" + k).split(",");
                final int count = Integer.parseInt(split[0]);
                option.setId(id);
                option.setLabel(v);
                option.setCount(count);
                options.add(option);
                this.totalCount += count;

                if (!"0".equals(split[2])) {
                    this.total = Integer.parseInt(split[2]);
                }
            }
        }
        // 计算占比
        options.forEach(option-> option.setCountPer(option.getCount()*1.0/this.totalCount));
    }
}
