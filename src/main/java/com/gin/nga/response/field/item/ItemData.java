package com.gin.nga.response.field.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.common.utils.StrUtils;
import com.gin.jackson.utils.JacksonUtils;
import com.gin.nga.deserializer.ItemStoreDataDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询商店道具数据
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 10:14
 */
@Getter
@Setter
@JsonDeserialize(using = ItemStoreDataDeserializer.class)
public class ItemData {
    List<ItemInfo> data = new ArrayList<>();
    boolean hasNext;
    int page;

    public ItemData(LinkedHashMap<String, Serializable> map) throws JsonProcessingException {
        this.page = (int) map.get("currentPage");
        this.hasNext = (int) map.getOrDefault("morePage", 0) == 1;
//        其他字段
        final LinkedHashMap<String, ItemTypeInfo> itemInfoMap = ItemTypeInfo.parse(map.get("itemInfo"));
//        道具信息
        for (Map.Entry<String, Serializable> entry : map.entrySet()) {
            String k = entry.getKey();
            Serializable v = entry.getValue();
            if (StrUtils.isNumber(k)) {
                final ItemInfo info = JacksonUtils.parseObj(v, ItemInfo.class);
                info.setTypeInfo(itemInfoMap.getOrDefault(info.getType().id + "_" + info.getSubType(), null));
                // 裁掉最后3位
                final Long price = info.getPrice();
                if (price!=null){
                    info.setPrice(price /1000);
                }
                data.add(info);
            }
        }
    }
}
