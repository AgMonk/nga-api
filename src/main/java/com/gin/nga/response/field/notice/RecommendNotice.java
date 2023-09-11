package com.gin.nga.response.field.notice;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Locale;

/**
 * 赞踩变化提醒
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 10:07
 */
@Getter
@Setter
public class RecommendNotice extends BaseNotice {
    /**
     * todo 含义未明
     */
    @JsonAlias("0")
    Integer type;

    @JsonAlias("3")
    Long repliedUid;
    /**
     * 主题标题
     */
    @JsonAlias("5")
    String topicTitle;
    /**
     * 主题id
     */
    @JsonAlias("6")
    Long topicId;
    /**
     * 回复id
     */
    @JsonAlias("7")
    Long replyId;
    /**
     * 页数
     */
    @JsonAlias("10")
    Integer page;

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = StringEscapeUtils.unescapeHtml4(topicTitle);
    }

    @Override
    public String getDescription() {
        return String.format(Locale.CHINA, "回复 [%d] 的赞踩数更新了", replyId);
    }
}
