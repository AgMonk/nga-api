package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

import static com.gin.common.utils.StrUtils.isNumber;

/**
 * 徽章
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 16:45
 */
@Getter
@Setter
@NoArgsConstructor
public class Medal {
    /**
     * 图片地址前缀 , 拼接文件名即为地址
     */
    public static final String URL_PREFIX = "https://img4.nga.178.com/ngabbs/medal/";
    /**
     * 文件名
     */
    @JsonAlias("icon")
    String filename;
    /**
     * 徽章名称
     */
    @JsonAlias("name")
    String name;
    /**
     * 描述
     */
    @JsonAlias("dscp")
    String description;
    /**
     * id
     */
    Integer id;

    public void setDescription(String tooltip) {
        this.description = tooltip;
        if (this.description != null) {
            this.description = tooltip.replace("徽章注释:", "").trim();
        }
    }

    public Medal(Map<Integer, Serializable> data) {
        this.filename = String.valueOf(data.get(0));
        this.name = String.valueOf(data.get(1));
        this.description = String.valueOf(data.get(2));
        this.id = Integer.valueOf(String.valueOf(data.get(3)));
    }

    public Integer getId() {
        if (id != null) {
            return id;
        }
        if (filename != null) {
            final String s = filename.substring(0, filename.indexOf("."));
            return isNumber(s) ? Integer.parseInt(s) : null;
        }
        return null;
    }

    public String getUrl() {
        return filename == null ? null : (URL_PREFIX + filename);
    }
}
