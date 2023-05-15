package com.gin.nga.params.nuke.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ListIntSerializer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 17.3 获得指定道具的信息
 * <a href="https://bbs.nga.cn/read.php?pid=130386150">文档</a>
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/15 09:38
 */
@Getter
public class ItemInfoParam extends NukeFuncParam {
    final String act = "info";
    @JsonSerialize(using = ListIntSerializer.class)
    final List<Integer> types;

    public ItemInfoParam(List<Integer> types) {
        super("item");
        this.types = types;
    }

    public ItemInfoParam(int type,List<Integer> subTypes) {
        this(merge(type, subTypes));
    }

    /**
     * 合并道具的类型和子类型
     * @param type 类型
     * @param subTypes 子类型
     * @return 合并列表
     */
    private static List<Integer> merge(int type,List<Integer> subTypes){
        final ArrayList<Integer> list = new ArrayList<>();
        subTypes.forEach(st->{
            list.add(type);
            list.add(st);
        });
        return list;
    }
}
