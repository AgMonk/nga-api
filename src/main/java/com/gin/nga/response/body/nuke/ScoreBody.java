package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.response.body.BaseMessageBody;
import lombok.Getter;
import lombok.Setter;

/**
 * 点赞和点踩的body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 17:16
 */
@Getter
@Setter
public class ScoreBody extends BaseMessageBody {
    /**
     * 变动情况
     */
    @JsonProperty("value")
    @JsonAlias("1")
    Integer value;

}   
