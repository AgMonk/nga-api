package com.gin.nga.utils;


import com.gin.common.utils.StrUtils;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.bbscode.entity.BbsTag;
import com.gin.nga.bbscode.enums.TagName;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 随机数工具类
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/15 14:22
 */
public class DiceUtils {
    private final static Pattern PATTERN = Pattern.compile("(\\d*)d(\\d+)");

    /**
     * 找出所有的dice标签
     *
     * @param nodes      节点数组
     * @param inCollapse 当前节点是否在 collapse 折叠块中
     */
    private static List<BbsTag> findAllDice(List<BbsTag> nodes, boolean inCollapse) {
        if (ObjectUtils.isEmpty(nodes)) {
            return new ArrayList<>();
        }
        final ArrayList<BbsTag> res = new ArrayList<>();

        for (BbsTag node : nodes) {
            if (node.getName() == TagName.dice) {
                //如果子节点是dice，加入列表
                node.setInCollapse(inCollapse);
                res.add(node);
            } else {
                // 否则继续到子节点中查询
                res.addAll(DiceUtils.findAllDice(node.getChildren(), inCollapse || node.getName() == TagName.collapse));
            }
        }

        return res;
    }

    /**
     * 排序
     *
     * @param nodes dice标签
     */
    private static List<BbsTag> sort(List<BbsTag> nodes) {
        if (ObjectUtils.isEmpty(nodes)) {
            return new ArrayList<>();
        }
        final List<BbsTag> inner = nodes.stream().filter(BbsTag::isInCollapse).collect(Collectors.toList());
        final ArrayList<BbsTag> res = nodes.stream().filter(bbsTag -> !bbsTag.isInCollapse()).collect(Collectors.toCollection(ArrayList::new));
        res.addAll(inner);
        return res;
    }

    /**
     * 根据种子计算一个新种子和结果
     *
     * @param seed 种子
     */
    public static DiceResult rnd(long seed) {
        // 计算一个新种子
        seed = (seed * 9301 + 49297) % 233280;
        // 返回一个结果
        return new DiceResult(seed, seed / 233280.0);
    }

    /**
     * dice
     * @param seed 种子
     * @param count 骰子面数
     * @return 结果
     */
    public static DiceResult dice(long seed,int count){
        final DiceResult result = rnd(seed);
        final double r = Math.floor(result.getResult() * count) + 1;
        return new DiceResult(result.getSeed(), r);
    }

    /**
     * 计算一个dice标签的结果，返回种子
     *
     * @param dice dice标签
     * @param seed 种子
     */
    private static long calculateDice(BbsTag dice, long seed) {
        if (dice == null || ObjectUtils.isEmpty(dice.getChildren())) {
            return seed;
        }
        // 总算式
        final String text = dice.getChildren().get(0).getRaw();
        // 总结果
        long sum = 0;
        final List<String> resultText = new ArrayList<>();
        // 算式分组计算结果
        for (String t : text.split("\\+")) {
            final Matcher matcher = PATTERN.matcher(t);
            if (matcher.find()) {
                int count = Integer.parseInt(matcher.group(1) != null ? matcher.group(1) : "1");
                int range = Integer.parseInt(matcher.group(2));
                for (int i = 0; i < count; i++) {
                    // 计算一次
                    DiceResult res = rnd(seed);
                    seed = res.getSeed();
                    int r = (int) (res.getResult() * range + 1);
                    sum += r;
                    resultText.add(String.format("%s(%d)", t, r));
                }
            } else if (StrUtils.isNumber(t)) {
                // 是纯数字
                final int i = Integer.parseInt(t);
                sum += i;
                resultText.add(String.valueOf(i));
            } else {
                resultText.add("格式错误");
            }
        }

        final String result = String.join(" + ", resultText);
        // 添加分组计算结果
        dice.getChildren().add(new BbsTag(TagName.text, " = " + result + " = " + sum));
        return seed;
    }

    /**
     * 计算
     *
     * @param nodes       节点列表
     * @param initialSeed 初始种子
     */
    public static void calculate(List<BbsTag> nodes, long initialSeed) {
        if (ObjectUtils.isEmpty(nodes)) {
            return;
        }
        // 找出所有的dice标签
        List<BbsTag> diceArray = DiceUtils.findAllDice(nodes,false);
        //  按照 collapse外 > collapse内排序
        List<BbsTag> sortedArray = DiceUtils.sort(diceArray);
        //计算每一个dice的随机结果

        // 初始化种子,如果所有dice都在collapse中，种子+1
        long seed = initialSeed + (sortedArray.get(0).isInCollapse()?1:0);
        for (BbsTag dice : sortedArray) {
            seed = DiceUtils.calculateDice(dice, seed);
        }
    }

    /**
     * 根据三个roll点结果确定当前随机种子
     * @param count 查找前x个
     * @param r1    结果1
     * @param r2    结果2
     * @param r3    结果3
     * @return 随机种子
     */
    public static List<Long> findSeed(int count, long r1, long r2, long r3){
        long seed = 0L;
        final ArrayList<Long> list = new ArrayList<>();
        while (true){
            final DiceResult res1 = dice(seed,100);
            final DiceResult res2 = dice(res1.getSeed(),100);
            final DiceResult res3 = dice(res2.getSeed(),100);

            if ((long)res1.getResult()==r1 && (long)res2.getResult()==r2 && (long)res3.getResult()==r3){
                list.add(seed);
            }
            seed++;
            if (list.size()==count){
                return list;
            }
        }
    }
}   
