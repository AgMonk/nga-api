package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * 查询版面权限信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/26 09:46
 */
@Getter
public class PrivilegeListParam extends NukeBaseParam {
    /**
     * 版面id
     */
    @JsonProperty("fid")
    final long forumId;

    public PrivilegeListParam(long forumId) {
        super("view_privilege", "view");
        this.forumId = forumId;
    }
}
