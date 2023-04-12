package com.gin.nga.params.thread;

import com.gin.nga.params.PageParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 合集主题搜索参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 09:32
 */
@Getter
@Setter
public class ColSearchParam extends PageParam {

    public ColSearchParam(int page) {
        super(page);
    }
}
