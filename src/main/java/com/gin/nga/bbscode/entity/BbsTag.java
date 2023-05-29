package com.gin.nga.bbscode.entity;

import com.gin.nga.bbscode.enums.TagName;
import lombok.Getter;
import lombok.Setter;

/**
 * 论坛标签\
 * @since : 2023/5/29 14:14
 * @author : ginstone
 * @version : v1.0.0
 */
@Getter
@Setter
public class BbsTag {
    /**
     * 标签名称
     */
    TagName name;
}   
