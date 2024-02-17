package com.gin.nga.response.body;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;

/**
 * 用户使用过的用户名
 * @author bx002
 * @since 2024/2/17 9:38
 */
@Getter
@Setter
public class UserOldNameBody {
    @JsonAlias("0")
    LinkedHashMap<Integer,OldName> data;
    @Getter
    @Setter
    public static class OldName{
        ZonedDateTime time;
        String username;
    }
}
