package com.gin.nga.response.forum;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.ForumGroupBodyDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 11:42
 */
@Getter
@Setter
@JsonDeserialize(using = ForumGroupBodyDeserializer.class)
public class ForumGroupBody {
    List<ForumGroup> groups;

    public void clear(){
        if (groups!=null){
            final Iterator<ForumGroup> it = groups.iterator();
            while (it.hasNext()) {
                final ForumGroup next = it.next();
                next.clear();
                if (next.isEmpty()){
                    it.remove();
                }
            }
        }
    }
}   
