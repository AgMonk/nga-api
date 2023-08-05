package com.gin.nga.utils;

import com.gin.nga.enums.SubForumType;

/**
 * 版面icon工具类
 *
 * @author bx002
 */
public class ForumIconUtils {
    public static final String ICON_FORMAT = "https://img4.nga.178.com/proxy/cache_attach/ficon/%du.png";
    public static final String ICON_BIG_FORMAT = "https://img4.nga.178.com/ngabbs/nga_classic/f/app/%d.png";
    public static final String ICON_FORMAT_COL = "https://img4.nga.178.com/proxy/cache_attach/ficon/%dv.png";

    public static String getIconUrl(SubForumType type, long id) {
        if (type == SubForumType.COL) {
            return String.format(ICON_FORMAT_COL, id);
        }
        if (type == SubForumType.FORUM) {
            return String.format(ICON_FORMAT, id);
        }
        return null;
    }

    public static String getIconUrl(Long forumId, Long colId) {
        if (colId != null && colId != 0) {
            return getIconUrl(SubForumType.COL, colId);
        }
        if (forumId != null) {
            return getIconUrl(SubForumType.FORUM, forumId);
        }
        return null;
    }

    public static String getBigIconUrl(long id) {
        return String.format(ICON_BIG_FORMAT,id);
    }

    public static String getBigIconUrl(Long forumId, Long colId) {
        if (colId != null && colId != 0) {
            return getBigIconUrl(colId);
        }
        if (forumId != null) {
            return getBigIconUrl(forumId);
        }
        return null;
    }
}