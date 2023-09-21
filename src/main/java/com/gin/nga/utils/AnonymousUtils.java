package com.gin.nga.utils;

/**
 * 匿名工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 16:03
 */
public class AnonymousUtils {
    /**
     * 匿名前缀
     */
    public static final String ANONYMOUS_PREFIX = "#anony_";

    private final static String T_1 = "甲乙丙丁戊己庚辛壬癸子丑寅卯辰巳午未申酉戌亥";
    private final static String T_2 = "王李张刘陈杨黄吴赵周徐孙马朱胡林郭何高罗郑梁谢宋唐许邓冯韩曹曾彭萧蔡潘田董袁于余叶蒋杜苏魏程吕丁沈任姚卢傅钟姜崔谭廖范汪陆金石戴贾韦夏邱方侯邹熊孟秦白江阎薛尹段雷黎史龙陶贺顾毛郝龚邵万钱严赖覃洪武莫孔汤向常温康施文牛樊葛邢安齐易乔伍庞颜倪庄聂章鲁岳翟殷詹申欧耿关兰焦俞左柳甘祝包宁尚符舒阮柯纪梅童凌毕单季裴霍涂成苗谷盛曲翁冉骆蓝路游辛靳管柴蒙鲍华喻祁蒲房滕屈饶解牟艾尤阳时穆农司卓古吉缪简车项连芦麦褚娄窦戚岑景党宫费卜冷晏席卫米柏宗瞿桂全佟应臧闵苟邬边卞姬师和仇栾隋商刁沙荣巫寇桑郎甄丛仲虞敖巩明佘池查麻苑迟邝";

    /**
     * 根据 #anony_ 开头的匿名id 生成中文匿名用户名
     * @param name 匿名id
     * @return 中文用户名
     */
    public static String getAnonymousName(String name) {
        int i = 6;
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 6; j++) {
            try {
                if (j == 0 || j == 3) {
                    sb.append(T_1.charAt(Integer.parseInt(name.substring(i + 1, i + 2), 16)));
                } else {
                    sb.append(T_2.charAt(Integer.parseInt(name.substring(i, i + 2), 16)));
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.err.println("生成匿名名称时下标越界, 忽略该位置: " + name);
            }
            i += 2;
        }
        return sb.toString();
    }

    /**
     * 判断一个用户id是否为匿名用户
     *
     * @param userId 用户id
     * @return 是否为匿名
     */
    public static boolean isAnonymous(String userId) {
        return userId != null && userId.startsWith(ANONYMOUS_PREFIX);
    }
}   
