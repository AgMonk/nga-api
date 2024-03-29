package com.gin.nga.response.field.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.jackson.utils.JacksonUtils;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.deserializer.UserFieldInReadDeserializer;
import com.gin.nga.method.ResourceApi;
import com.gin.nga.response.CommonUiData;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.field.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 用户信息上下文
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:58
 */
@Getter
@Setter
@JsonDeserialize(using = UserFieldInReadDeserializer.class)
@NoArgsConstructor
public class UserContext {

    /**
     * 匿名前缀
     */
    public static final String ANONYMOUS_PREFIX = "-";
    /**
     * 用户组信息字段名
     */
    public static final String GROUPS_FIELD = "__GROUPS";
    /**
     * 徽章信息字段名
     */
    public static final String MEDALS_FIELD = "__MEDALS";
    /**
     * 声望信息字段名
     */
    public static final String REPUTATIONS_FIELD = "__REPUTATIONS";

    /**
     * 常规用户信息 uid->info
     */
    Map<Long, UserInfoRead> userInfo = new LinkedHashMap<>();
    /**
     * 匿名用户信息 临时id->info
     */
    Map<Integer, AnonymousUser> anonymousUserInfo = new LinkedHashMap<>();
    /**
     * 用户组信息 id->info
     */
    Map<Integer, UserGroup> groups = new LinkedHashMap<>();
    /**
     * 徽章信息 id->info
     */
    Map<Integer, Medal> medals = new LinkedHashMap<>();
    /**
     * 声望信息 id -> uid -> 声望
     */
    ReputationUser reputationUser;

    /**
     * ui相关数据,头像buff相关需要，可以提前手动提供，如果使用时未提供会自动请求
     */
    CommonUiData commonUiData;

    public UserContext(LinkedHashMap<String, Object> inputMap) {
        inputMap.forEach((key, obj) -> {
            if (GROUPS_FIELD.equals(key)) {
                //用户组信息
                if (obj instanceof LinkedHashMap<?, ?>) {
                    LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) obj;
                    map.forEach((k, v) -> {
                        final int id = Integer.parseInt(String.valueOf(k));
                        try {
                            final String s = NgaRes.MAPPER.writeValueAsString(v);
                            Map<Integer, Serializable> data = NgaRes.MAPPER.readValue(s, new TypeReference<Map<Integer, Serializable>>() {
                            });
                            groups.put(id, new UserGroup(data));
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } else if (MEDALS_FIELD.equals(key)) {
                //徽章信息
                if (obj instanceof LinkedHashMap<?, ?>) {
                    LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) obj;
                    map.forEach((k, v) -> {
                        final int id = Integer.parseInt(String.valueOf(k));
                        try {
                            final String s = NgaRes.MAPPER.writeValueAsString(v);
                            Map<Integer, Serializable> data = NgaRes.MAPPER.readValue(s, new TypeReference<Map<Integer, Serializable>>() {
                            });
                            medals.put(id, new Medal(data));
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } else if (REPUTATIONS_FIELD.equals(key)) {
                //声望信息
                try {
                    final String s = NgaRes.MAPPER.writeValueAsString(obj);
                    this.reputationUser = NgaRes.MAPPER.readValue(s, ReputationUser.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

            } else if (key.startsWith(ANONYMOUS_PREFIX)) {
                // 匿名用户信息
                final String username = String.valueOf(JacksonUtils.jsonToMap(obj).get("username"));
                final AnonymousUser anonymousUser = new AnonymousUser();
                final int index = Integer.parseInt(key);
                anonymousUser.setIndex(index);
                anonymousUser.setUsername(username);
                anonymousUserInfo.put(index, anonymousUser);
            } else {
                // 常规用户信息
                try {
                    userInfo.put(Long.parseLong(key), JacksonUtils.parseObj(obj, UserInfoRead.class));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static String getReputationLabel(List<CustomLevel> customLevels, int value) {
        final CustomLevel level = customLevels.stream()
                .filter(l -> value >= l.getRank())
                .min((o1, o2) -> o2.getRank() - o1.getRank())
                .orElse(null);
        return level==null?customLevels.get(0).getName():level.getName();
    }

    /**
     * 随机头像
     * @param userInfo 用户信息
     * @return 随机头像
     */
    private static String getRandomAvatar(UserInfoRead userInfo) {
        //随机头像
        final List<String> avatars = userInfo.getAvatars();
        if (!ObjectUtils.isEmpty(avatars)) {
            final int i = new Random().nextInt(avatars.size());
            return avatars.get(i);
        }
        return null;
    }

    /**
     * 获取用户信息
     *
     * @param userId       用户id
     * @param customLevels {@link  Forum }中的同名字段
     * @return {@link UserInfoContext} 或 {@link AnonymousUser}
     */
    public Object getUserInfo(Long userId, List<CustomLevel> customLevels) {
        if (userId < 0) {
            //匿名用户
            return this.anonymousUserInfo.get(Integer.parseInt(userId.toString()));
        }
        final UserInfoRead userInfoRead = this.userInfo.get(userId);
        if (userInfoRead == null) {
            return null;
        }
        final UserInfoContext res = new UserInfoContext(userInfoRead);
        //用户组
        res.setGroup(this.groups.get(userInfoRead.getMemberId()));

        // 头像buff
        final UserBuff userBuff = userInfoRead.getAvatarUserBuff();
        if (userBuff != null) {
            //有头像buff 按照buff修改头像
            try {
                commonUiData = commonUiData==null? ResourceApi.commonUi(new OkHttpClient()).sync():commonUiData;
                final AvatarBuff avatarBuff = commonUiData.findAvatarBuffById(Math.toIntExact(userBuff.getExtraData()));
                res.setAvatarBuff(avatarBuff);
                // 设置头像
                res.setAvatar(avatarBuff != null ? avatarBuff.getUrl() : getRandomAvatar(userInfoRead));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            res.setAvatar(getRandomAvatar(userInfoRead));
        }
        //徽章
        final List<Integer> medalIds = userInfoRead.getMedalIds();
        if (!ObjectUtils.isEmpty(medalIds)) {
            res.setMedals(medalIds.stream().map(id -> this.medals.get(id)).collect((Collectors.toList())));
        }
        //声望
        if (!ObjectUtils.isEmpty(customLevels)) {
            final int value = this.reputationUser.getData().getOrDefault(userId, 0);
            final String label = getReputationLabel(customLevels, value);
            res.setReputation(new LabelValue(label, value));
        }
        return res;
    }
}
