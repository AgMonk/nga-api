package com.gin.nga.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.common.utils.JacksonUtils;
import com.gin.nga.document.Navigation;
import com.gin.nga.response.field.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
    /**
     * 是否为兼容模式, 即，数据从网页中解析获得
     */
    boolean document = false;

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
    Integer size = 20;
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
        this.document = true;
        // 导航栏
        final Element nav = document.getElementsByClass("nav").first();
        assert nav != null;
        final Navigation navigation = new Navigation(nav);

        JacksonUtils.printPretty(navigation);

        //todo 版面信息


        //todo 当前页码
        //todo 楼层总数(含主楼)
        //todo 每页回复数
        //todo 主题信息
        //todo 用户相关信息
        //todo 回复信息
    }
}
