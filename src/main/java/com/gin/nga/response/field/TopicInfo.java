package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import com.gin.nga.enums.EntranceType;
import com.gin.nga.enums.ReplyStatus;
import com.gin.nga.utils.AnonymousUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static com.gin.nga.params.post.PostParam.REPLY_ONCE;

/**
 * 主题信息
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 11:27
 */
@Getter
@Setter
public class TopicInfo extends TopicInfoSimple {
    /**
     * 回复(出现在收藏和搜索用户回复接口)
     */
    @JsonProperty("__P")
    ReplySimple reply;
    /**
     * 作者用户名
     */
    @JsonProperty("author")
    String authorName;
    /**
     * 未知数据
     */
    @JsonProperty("jdata")
    String jData;
    /**
     * 最后改动时间
     */
    @JsonProperty("lastmodify")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime lastModify;
    /**
     * 最后回复时间
     */
    @JsonProperty("lastpost")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime lastPost;
    /**
     * 最后回复的用户名
     */
    @JsonProperty("lastposter")
    String lastPosterName;
    /**
     * 所属合集信息, 字段"1" 为 fid, "2"为版面名称
     */
    @JsonProperty("parent")
    TopicParent topicParent;
    /**
     * 从某个主题镜像而来
     */
    @JsonProperty("quote_from")
    Long quoteFromTopicId;
    /**
     * 推荐分
     */
    @JsonProperty("recommend")
    Integer recommendScore;
    /**
     * 回复数
     */
    @JsonProperty("replies")
    Integer replies;
    /**
     * 主题标题字体信息
     */
    @JsonProperty("topic_misc")
    TitleFont titleFont;
    /**
     * 额外信息, 字段"1" 为 32时，表示本条为版面入口(字段"3"为fid), 为 33 时为合集入口,字段tid即为stid
     */
    @JsonProperty("topic_misc_var")
    Map<Long, Serializable> topicMiscVar;
    /**
     * 主题url
     */
    @JsonProperty("tpcurl")
    String url;


    /**
     * 总页数
     */
    public Integer getTotalPage() {
        if (this.replies == null) {
            return null;
        }
        final int size = 20;
        final int p = this.replies / size;
        final int m = this.replies % size;
        return p + (m > 0 ? 1 : 0);
    }

    /**
     * 作者匿名
     *
     * @param authorName 作者用户名
     */
    public void setAuthorName(String authorName) {
        if (authorName != null && authorName.startsWith(AnonymousUtils.ANONYMOUS_PREFIX)) {
            this.authorName = AnonymousUtils.getAnonymousName(authorName);
        } else {
            this.authorName = authorName;
        }
    }

    /**
     * 主题是否为单帖回复
     * @return 是否为单帖回复
     */
    public boolean isReplyOnce(){
        final boolean b1 = titleFont != null && titleFont.isReplyOnce();
        final boolean b2 = topicMiscVar != null && String.valueOf(REPLY_ONCE).equals(String.valueOf(topicMiscVar.get(1L)));
        return b1 || b2;
    }
    public Long getForumId() {
        final EntranceType type = getEntranceType();
        if (type == EntranceType.FORUM) {
            final Serializable s = this.topicMiscVar.get(3L);
            if (s != null) {
                return Long.valueOf(s.toString());
            }
        }
        return forumId;
    }

    public EntranceType getEntranceType() {
        final Integer flag = 32;
        final List<ReplyStatus> status = this.getStatus();
        if (status != null && status.contains(ReplyStatus.IS_SET)) {
            return EntranceType.COL;
        } else if (this.topicMiscVar != null && flag.equals(this.topicMiscVar.get(1L))) {
            return EntranceType.FORUM;
        } else {
            return EntranceType.TOPIC;
        }
    }
}
