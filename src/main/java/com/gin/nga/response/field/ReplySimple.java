package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.nga.enums.ReplyStatus;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 回复(简单数据), 出现在收藏回复中
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 12:23
 */
@Getter
@Setter
public class ReplySimple {
    /**
     * 作者uid
     */
    @JsonAlias("authorid")
    Long authorUid;
    /**
     * 回复正文
     */
    @JsonAlias("content")
    String content;
    /**
     * 回复id
     */
    @JsonAlias("pid")
    Long replyId;
    /**
     * 回复或引用的id
     */
    @JsonAlias("reply_to")
    Long replyToId;
    /**
     * 发表时间
     */
    @JsonAlias("postdate")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime postDatetime;
    /**
     * 标题
     */
    @JsonAlias("subject")
    String title;
    /**
     * 主题id
     */
    @JsonAlias("tid")
    Long topicId;
    /**
     * 类型
     */
    @JsonAlias("type")
    Integer type;

    /**
     * 回复状态
     * @return 回复状态
     */
    public List<ReplyStatus> getStatus(){
        return type==null?null:ReplyStatus.parse(type);
    }

    public void setContent(String content) {
        this.content = StringEscapeUtils.unescapeHtml4(StringEscapeUtils.unescapeHtml4(content));
    }

    public void setTitle(String title) {
        this.title = StringEscapeUtils.unescapeHtml4(title);
    }
}
