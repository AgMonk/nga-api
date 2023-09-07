package com.gin.nga.response.field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 论坛货币
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 11:04
 */
@Getter
@Setter
@NoArgsConstructor
public class Money {
    /**
     * 原始数据(铜币数量)
     */
    Integer raw;
    /**
     * 换算后的金币
     */
    Integer gold;
    /**
     * 换算后的银币
     */
    Integer silver;
    /**
     * 换算后的铜币
     */
    Integer copper;


    public Money(int raw) {
        final int h = 100;
        this.raw = raw;

        int temp = raw;

        this.copper = temp % h;
        temp = (temp - this.copper) / h;
        this.silver = temp % h;
        temp = (temp - this.silver) / h;
        this.gold = temp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (gold > 0) {
            sb.append(gold).append(" 金");
        }
        if (silver > 0) {
            sb.append(silver).append(" 银");
        }
        if (copper > 0) {
            sb.append(copper).append(" 铜");
        }
        return sb.toString();
    }
}
