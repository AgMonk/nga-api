package com.gin.nga.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.BoolParam;
import com.gin.nga.enums.OrderByParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 17:26
 */
@Getter
@Setter
public class ThreadParam extends PageParam {
    /**
     * 收藏夹id
     */
    @JsonProperty("favor")
    Long folderId;
    /**
     * 版面id
     */
    @JsonProperty("fid")
    Long forumId;
    /**
     * 合集主题Id
     */
    @JsonProperty("stid")
    Long colTid;
    /**
     * 搜索关键字
     */
    String key;
    /**
     * 是否搜索主楼正文
     */
    @JsonProperty("content")
    BoolParam searchContent;
    /**
     * 是否只搜索精华帖
     */
    @JsonProperty("recommend")
    BoolParam recommendOnly;
    /**
     * 作者uid
     */
    @JsonProperty("authorid")
    Long authorId;
    /**
     * 传入任意值表示搜索用户的回复
     */
    @JsonProperty("searchpost")
    Integer searchPost;
    /**
     * 排序方式
     */
    @JsonProperty("order_by")
    OrderByParam orderBy;
    public ThreadParam(int page) {
        super(page);
    }
}
