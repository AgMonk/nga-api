package com.gin.nga.api;

import com.gin.nga.call.NgaJsonCall;
import com.gin.nga.params.nuke.ScoreParam;
import com.gin.nga.response.body.nuke.ScoreBody;
import lombok.RequiredArgsConstructor;

/**
 * 赞踩相关API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 13:38
 */
@RequiredArgsConstructor
public class NgaScoreApi {
    private final NgaClient client;
    /**
     * 点赞或点踩
     * @param param 参数
     * @return com.gin.nga.call.NgaJsonCall<com.gin.nga.response.body.nuke.RecommendBody>
     * @author bx002
     * @since 2023/4/19 14:31
     */
    public NgaJsonCall<ScoreBody> score(ScoreParam param) {
        return client.nuke(param, ScoreBody.class);
    }

}
