package com.gin.nga.utils;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * dice计算结果
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/15 14:38
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiceResult {
    long seed;
    double result;
}
