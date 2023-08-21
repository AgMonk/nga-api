package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.response.field.notice.BaseNotice;
import com.gin.nga.response.field.notice.MessageNotice;
import com.gin.nga.response.field.notice.RecommendNotice;
import com.gin.nga.response.field.notice.ReplyNotice;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * 下一次执行请求带的time参数
     */
    long nextTime;

    /**
     * 统计未读数量
     *
     * @return 未读数量
     */
    public long countUnread() {
        long count = 0;
        if (!ObjectUtils.isEmpty(replyNotices)) {
            count += BaseNotice.countUnread(replyNotices);
        }
        if (!ObjectUtils.isEmpty(msgNotices)) {
            count += BaseNotice.countUnread(msgNotices);
        }
        if (!ObjectUtils.isEmpty(recommendNotices)) {
            count += BaseNotice.countUnread(recommendNotices);
        }
        return count;
    }

    /**
     * 合并数据
     *
     * @param newData 新数据
     */
    public void merge(NoticeData newData) {
        this.replyNotices = this.replyNotices == null ? new ArrayList<>() : this.replyNotices;
        this.msgNotices = this.msgNotices == null ? new ArrayList<>() : this.msgNotices;
        this.recommendNotices = this.recommendNotices == null ? new ArrayList<>() : this.recommendNotices;
        if (!ObjectUtils.isEmpty(newData.getReplyNotices())) {
            this.replyNotices.addAll(newData.getReplyNotices());
        }
        if (!ObjectUtils.isEmpty(newData.getMsgNotices())) {
            this.msgNotices.addAll(newData.getMsgNotices());
        }
        if (!ObjectUtils.isEmpty(newData.getRecommendNotices())) {
            this.recommendNotices.addAll(newData.getRecommendNotices());
        }

        final long t0 = this.replyNotices.stream().mapToLong(i -> i.getTimestamp().toEpochSecond()).max().orElse(0);
        final long t1 = this.msgNotices.stream().mapToLong(i -> i.getTimestamp().toEpochSecond()).max().orElse(0);
        final long t2 = this.recommendNotices.stream().mapToLong(i -> i.getTimestamp().toEpochSecond()).max().orElse(0);
        final long t3 = timestamp != null ? timestamp.toEpochSecond() : 0;

        final long[] longs = {t0, t1, t2, t3};
        this.nextTime = Arrays.stream(longs).max().getAsLong();
    }

    /**
     * 清空数据
     */
    public void clear() {
        this.replyNotices = new ArrayList<>();
        this.msgNotices = new ArrayList<>();
        this.recommendNotices = new ArrayList<>();
    }

    /**
     * 全部已读
     */
    public void readAll() {
        BaseNotice.readAll(replyNotices);
        BaseNotice.readAll(msgNotices);
        BaseNotice.readAll(recommendNotices);
    }

    public int count() {
        return count(recommendNotices) + count(msgNotices) + count(replyNotices);
    }

    private static int count(List<?> list) {
        return list == null ? 0 : list.size();
    }
}
