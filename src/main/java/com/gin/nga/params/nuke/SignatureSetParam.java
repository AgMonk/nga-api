package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * 设置签名参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 17:53
 */
@Getter
public class SignatureSetParam extends NukeBaseParam {
    final int raw = 3;
    @JsonProperty("uid")
    final long userId;
    @JsonProperty("sign")
    final String signature;

    public SignatureSetParam(long userId, String signature) {
        super("set_sign", "set");
        this.userId = userId;
        this.signature = signature;
    }
}
