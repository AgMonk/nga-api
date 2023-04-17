package com.gin.nga.params.read;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    Long topicId;
    /**
     * 页码
     */
    @JsonProperty("page")
    Integer page;
    /**
     * 作者uid(只看TA功能)
     */
    @JsonProperty("authorid")
    Long authorUid;
}   
