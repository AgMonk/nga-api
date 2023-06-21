package com.gin.nga.response.field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 键值对
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/3 09:14
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LabelValue {
    String label; Serializable value;
}   
