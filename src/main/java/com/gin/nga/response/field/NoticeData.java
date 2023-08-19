package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.response.field.notice.MessageNotice;
import com.gin.nga.response.field.notice.RecommendNotice;
import com.gin.nga.response.field.notice.ReplyNotice;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 提醒消息body
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 09:24
 */
@Getter
@Setter
public class NoticeData {
    @JsonAlias("0")
    List<ReplyNotice> replyNotices;
    @JsonAlias("1")
    List<MessageNotice> msgNotices;
    @JsonAlias("2")
    List<RecommendNotice> recommendNotices;
    /**
     * 获得时间
     */
    @JsonAlias("lasttime")
    ZonedDateTime timestamp;

    long time;

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
        if (timestamp != null) {
            time = timestamp.toEpochSecond();
        }
    }

    /**
     * 统计未读数量
     * @return 未读数量
     */
    public long countUnread(){
        long count = 0;
        if (!ObjectUtils.isEmpty(replyNotices)) {
            count += replyNotices.stream().filter(i->!i.isRead()).count();
        }
        if (!ObjectUtils.isEmpty(msgNotices)) {
            count += msgNotices.stream().filter(i->!i.isRead()).count();
        }
        if (!ObjectUtils.isEmpty(recommendNotices)) {
            count += recommendNotices.stream().filter(i->!i.isRead()).count();
        }
        return count;
    }

    /**
     * 合并数据
     * @param newData 新数据
     */
    public void merge(NoticeData newData){
        setTimestamp(newData.getTimestamp());
        if (!ObjectUtils.isEmpty(newData.getReplyNotices())){
            this.replyNotices = this.replyNotices==null?new ArrayList<>():this.replyNotices;
            this.replyNotices.addAll(newData.getReplyNotices());
        }
        if (!ObjectUtils.isEmpty(newData.getMsgNotices())){
            this.msgNotices = this.msgNotices==null?new ArrayList<>():this.msgNotices;
            this.msgNotices.addAll(newData.getMsgNotices());
        }
        if (!ObjectUtils.isEmpty(newData.getRecommendNotices())){
            this.recommendNotices = this.recommendNotices==null?new ArrayList<>():this.recommendNotices;
            this.recommendNotices.addAll(newData.getRecommendNotices());
        }
    }
}
