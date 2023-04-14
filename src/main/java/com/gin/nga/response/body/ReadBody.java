package com.gin.nga.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.response.field.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * read.php 的响应data
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:40
 */
@Getter
@Setter
@NoArgsConstructor
public class ReadBody {

    @JsonProperty("__CU")
    CurrentUser currentUser;

    @JsonProperty("__F")
    Forum forum;
    /**
     * 当前页码
     */
    @JsonProperty("__PAGE")
    Integer page;
    /**
     * 楼层总数(含主楼)
     */
    @JsonProperty("__ROWS")
    Integer total;
    /**
     * 每页回复数
     */
    @JsonProperty("__R__ROWS_PAGE")
    Integer size;
    /**
     * 主题信息
     */
    @JsonProperty("__T")
    TopicInfoInRead topicInfo;
    /**
     * 用户相关信息
     */
    @JsonProperty("__U")
    UserFieldInRead userInfoField;
    /**
     * 回复信息
     */
    @JsonProperty("__R")
    Map<Integer, ReplyInfo> replies;

    public ReadBody(Document document) {
        //todo 版面信息
        //todo 当前页码
        //todo 楼层总数(含主楼)
        //todo 每页回复数
        //todo 主题信息
        //todo 用户相关信息
        //todo 回复信息
    }
}
