package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.EditHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * @author bx002
 * @since 2024/1/20 13:46
 */
@Getter
@Setter
public class EditHistoryBody {
    @JsonAlias("0")
    LinkedHashMap<Integer, EditHistory> data;
}
