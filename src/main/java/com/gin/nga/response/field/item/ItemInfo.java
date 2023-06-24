package com.gin.nga.response.field.item;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import com.gin.nga.enums.ItemType;
import com.gin.nga.enums.TradeBit;
import com.gin.nga.response.field.Medal;
import com.gin.nga.response.field.Money;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * 商店里的道具信息
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 10:15
 */
@Getter
@Setter
public class ItemInfo {
    public static final String URL_PREFIX = "https://img4.nga.178.com/ngabbs/nga_classic/items/";
    /**
     * 道具id
     */
    @JsonAlias("id")
    Long id;
    @JsonAlias("t_index")
    Integer index;
    @JsonAlias("uid")
    Long uid;
    /**
     * 剩余数量
     */
    @JsonAlias("count")
    Long count;
    /**
     * 价格
     */
    @JsonAlias("price")
    Integer price;
    /**
     * todo 疑似为创建时间
     */
    @JsonSerialize(using = ZdtJsonSerializer.class)
    @JsonAlias("time")
    ZonedDateTime time;
    @JsonAlias("trade_bit")
    TradeBit tradeBit;
    @JsonAlias("bit")
    Long bit;
    /**
     * 类型
     */
    @JsonAlias("type")
    ItemType type;
    /**
     * 子类型
     */
    @JsonAlias("sub_type")
    Integer subType;
    /**
     * 类型信息
     */
    ItemTypeInfo typeInfo;

    Money money;

    public String getUrl(){
        if (typeInfo==null){
            return null;
        }

        if (type==ItemType.MEDAL){
            return Medal.URL_PREFIX+typeInfo.getIcon();
        }
        return URL_PREFIX+typeInfo.getIcon();
    }

}   
