package com.gin.nga.response.field;

import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.enums.TitleColor;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;

/**
 * 主题的标题字体
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/15 16:35
 */
@Getter
@Setter
public class TitleFont {
    TitleColor color;
    /**
     * 加粗
     */
    boolean bold;
    /**
     * 下划线
     */
    boolean lineThrough;
    /**
     * 斜体
     */
    boolean italic;
    /**
     * 单帖回复
     */
    boolean replyOnce;

    public TitleFont(String s) {
        final String data = decode(s);
        if (data == null) {
            this.color = TitleColor.DEFAULT;
            return;
        }
        String colorData = data.substring(0, 5);
        String fontData = data.substring(5);
        this.color = TitleColor.find(colorData);
        this.bold = fontData.charAt(0) == '1';
        this.italic = fontData.charAt(1) == '1';
        this.lineThrough = fontData.charAt(2) == '1';
        this.replyOnce = fontData.length() > 25 && fontData.charAt(25) == '1';
    }

    /**
     * 解码
     *
     * @param s topic_misc
     * @return 明文
     */
    private static String decode(String s) {
        if (ObjectUtils.isEmpty(s)) {
            return null;
        }
        final Base64.Decoder decoder = Base64.getDecoder();

        byte[] bytes = decoder.decode(s);

        for (int i = 0; i < bytes.length; i += 5) {
            byte first = bytes[i];
            //截取后续4个字节数据，转换成2进制字符串
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 4; j++) {
                sb.append(byte2String(bytes[i + j + 1]));
            }
            if (first == 1) {
                //字体数据
                return sb.reverse().toString();
            }
            if (first == 2) {
                //集合id
            }
        }
        return null;
    }

    private static String byte2String(byte b) {
        return Integer.toBinaryString((b & 0xFF) + 0x100).substring(1);
    }
}
