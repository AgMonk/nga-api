package com.gin.nga.response.vote;

import com.gin.common.utils.StrUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菠菜数据
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/1 10:25
 */
@Getter
@Setter
public class BetData extends SuperVoteData {
    /**
     * 最小投注金额
     */
    Integer min;
    /**
     * 最大投注金额
     */
    Integer max;
    /**
     * 总投注数
     */
    long totalMoney = 0L;
    /**
     * 选项
     */
    List<BetOption> options = new ArrayList<>();
    /**
     * 胜出id
     */
    Integer successId;

    public BetData(String s) {
        super(s);

        for (Map.Entry<String, String> entry : this.map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (StrUtils.isNumber(k)) {
                final BetOption option = new BetOption();
                final int id = Integer.parseInt(k);
                final String[] split = this.map.get("_" + k).split(",");
                final int count = Integer.parseInt(split[0]);
                final long money = Long.parseLong(split[1]);
                option.setId(id);
                option.setLabel(v);
                option.setCount(count);
                option.setMoney(money);
                options.add(option);
                this.totalCount += count;
                this.totalMoney += money;
            } else if ("min".equals(k)) {
                this.min = Integer.valueOf(v);
            } else if ("max".equals(k)) {
                this.max = Integer.valueOf(v);
            }else if ("done".equals(k)) {
                // 胜出项目
                this.successId = Integer.valueOf(v);
            }
        }
        // 计算占比
        options.forEach(option -> {
            option.setCountPer(option.getCount() * 1.0 / this.totalCount);
            option.setMoneyPer(option.getMoney() * 1.0 / this.totalMoney);
        });
    }
}
