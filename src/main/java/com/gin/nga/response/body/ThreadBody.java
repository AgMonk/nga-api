package com.gin.nga.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.response.field.CurrentUser;
import com.gin.nga.response.field.Forum;
import lombok.Getter;
import lombok.Setter;

/**
 * thread.php的响应data
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 16:46
 */
@Getter
@Setter
public class ThreadBody {
    @JsonProperty("__CU")
    CurrentUser currentUser;

    @JsonProperty("__F")
    Forum forum;
}
