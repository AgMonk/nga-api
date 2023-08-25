package com.gin.nga.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gin.jackson.utils.JacksonUtils;
import com.gin.nga.response.field.AvatarBuff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UI相关数据
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/28 14:33
 */
@Getter
@Setter
@NoArgsConstructor
public class CommonUiData {
    public static final Pattern AVATAR_BUFF_PATTERN = Pattern.compile("(\\d+) : (.+)");
    /**
     * 头像buff
     */
    List<AvatarBuff> avatarBuffs = new ArrayList<>();

    public CommonUiData(String res) {
        final String[] lines = res.split("\n");
        boolean enable = false;
        for (String line : lines) {
            String l =line.trim();
            // 开始
            if (l.startsWith("var __AVATAR_LIST = {")) {
                enable = true;
                continue;
            }
            // 结束
            if (enable && "}".equals(l)){
                break;
            }
            // 注释跳过
            if (l.startsWith("//")) {
                continue;
            }
            // 记录数据
            if (enable) {
                if (l.startsWith(",")) {
                    l = l.substring(1);
                }
                final Matcher matcher = AVATAR_BUFF_PATTERN.matcher(l);
                if (matcher.find()) {
                    final AvatarBuff avatarBuff = new AvatarBuff();
                    avatarBuff.setId(Integer.parseInt(matcher.group(1)));
                    final String g2 = matcher.group(2).replace("'","\"");
                    if (g2.startsWith("[")) {
                        try {
                            List<String> list = JacksonUtils.MAPPER.readValue(g2, new TypeReference<List<String>>() {
                            });
                            avatarBuff.setFilename(list.get(0));
                            avatarBuff.setSuffix(list.get(1));
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    } else {
                        avatarBuff.setFilename(g2.replace("\"",""));
                    }
                    avatarBuffs.add(avatarBuff);
                }
            }
        }
        if (avatarBuffs.isEmpty()){
            System.err.println("[警告] 未解析到头像buff数据, 请检查文件格式是否被修改");
        }
    }

    /**
     * 通过id查询头像buff
     * @param id id
     * @return 头像buff
     */
    public AvatarBuff findAvatarBuffById(int id){
        return avatarBuffs.stream().filter(i->i.getId()==id).findFirst().orElse(null);
    }
}
