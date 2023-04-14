package com.gin.nga.document;

import com.gin.nga.utils.NgaLink;
import lombok.Getter;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.nodes.Element;

/**
 * 由a标签解析来的链接
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/14 11:59
 */
@Getter
public class DocLink {
    String title;
    NgaLink link;
    public DocLink(Element a) {
        this.title = StringEscapeUtils.unescapeHtml4(a.ownText());
        this.link = new NgaLink(a.attr("href"));
    }


}
