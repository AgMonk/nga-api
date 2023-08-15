package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import com.gin.nga.enums.ReplyStatus;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 简单的主题信息
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/7 09:43
 **/
@Getter
@Setter
public class TopicInfoSimple {
    /**
     * 主题id
     */
    @JsonProperty("tid")
    Long topicId;
    /**
     * 版面id
     */
    @JsonProperty("fid")
    Long forumId;
    /**
     * 标题
     */
    @JsonProperty("subject")
    String title;
    /**
     * 类型
     */
    @JsonProperty("type")
    Integer type;
    /**
     * 作者uid
     */
    @JsonProperty("authorid")
    String authorUid;

    /**
     * 发表时间
     */
    @JsonProperty("postdate")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime postDatetime;

    /**
     * 主题状态
     *
     * @return 主题状态
     */
    public List<ReplyStatus> getStatus() {
        return type == null ? null : ReplyStatus.parse(type);
    }

    public void setTitle(String title) {
        // 反转义
        this.title = title!=null?StringEscapeUtils.unescapeHtml4(title):null;
    }
}
