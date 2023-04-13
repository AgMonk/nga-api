package com.gin.nga.params.thread;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.BoolParam;
import com.gin.nga.params.PageParam;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户的主题/回复搜索参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
public class UserSearchParam extends PageParam {
    /**
     * 作者uid
     */
    @JsonProperty("authorid")
    @NotNull
    Long authorUid;
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
    BoolParam recommendOnly;
}
