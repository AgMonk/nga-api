package com.gin.nga.params.read;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 获取主题内容的参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 11:20
 */
@Getter
@Setter
public class ReadTopicParam {
    /**
     * 主题id
     */
    @JsonProperty("tid")
    final long topicId;
    /**
     * 页码
     */
    @JsonProperty("page")
    final int page;
    /**
     * 作者uid(只看TA功能)
     */
    @JsonProperty("authorid")
    Long authorUid;

    /**
     *  常规浏览
     * @param topicId 主题ID
     * @param page 页码
     * @author bx002
     * @since 2023/4/19 14:28
     */
    public ReadTopicParam(long topicId, int page) {
        this(topicId, page, null);
    }
/**
 *  完整参数
 * @param topicId 主题ID
 * @param page 页码
 * @param authorUid 作者uid
 * @author bx002
 * @since 2023/4/19 14:28
 */
    public ReadTopicParam(long topicId, int page, Long authorUid) {
        this.topicId = topicId;
        this.page = Math.max(1, page);
        this.authorUid = authorUid;
    }


}
