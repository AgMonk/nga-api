package com.gin.nga.document;

import com.gin.nga.enums.NgaLinkType;
import lombok.Getter;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 导航栏(网页解析)
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/14 11:55
 */
@Getter
public class Navigation {

    List<DocLink> links;

    public Navigation(Element element) {
        this.links = element.getElementsByTag("a").stream().map(DocLink::new).collect(Collectors.toList());
    }

    /**
     * 查找列表中最后一个指定类型的链接
     * @param type 类型
     * @return 链接
     */
    public DocLink findLast(NgaLinkType type) {
        if (type == null || links == null) {
            return null;
        }
        final List<DocLink> list = this.links.stream().filter(i -> i.getLink().getType() == type).toList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }
}
