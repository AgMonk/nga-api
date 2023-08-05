package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.BooleanJsonSerializer;
import com.gin.nga.deserializer.BooleanDeserializer;
import com.gin.nga.params.PageParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户的主题/回复搜索参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
@NoArgsConstructor
public class UserSearchParam extends PageParam {
    /**
     * 作者uid
     */
    @JsonProperty("authorid")
    long authorUid;
    /**
     * 版面id
     */
    @JsonProperty("fid")
    Long forumId;
    /**
     * 传入任意值表示搜索用户的回复
     */
    @JsonProperty("searchpost")
    Integer searchReply;
    /**
     * 是否只搜索精华帖
     */
    @JsonProperty("recommend")
    @JsonSerialize(using = BooleanJsonSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    Boolean recommendOnly;

    public UserSearchParam(long authorUid, Serializable page, Long forumId, boolean searchReply, Boolean recommendOnly) {
        super(page);
        this.authorUid = authorUid;
        this.forumId = forumId;
        this.searchReply = searchReply ? 1 : null;
        this.recommendOnly = recommendOnly;
    }
}
