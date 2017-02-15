package com.imp.lf.entities;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;


import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/19
 */
@TableName("t_message")
public class LFMessage implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "is_delete")
    private int isDelete;
    private String type;
    @TableField(value = "create_time")
    private Date createTime;
    private String title;
    private double latitude;
    private double longitude;
    private String kind;
    private String detail;
    @TableField(value = "thing_time")
    private Date thingTime;
    @TableField(value = "phone_number")
    private String phoneNumber;

    @TableField(value = "share_count")
    private Long shareCount;
    @TableField(value = "comments_count")
    private Long commentsCount;
    @TableField(value = "attention_count")
    private Long attentionCount;

    private transient User user;

    private transient List<String> imageUrls;

   private transient  List<Words> wordsList;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getThingTime() {
        return thingTime;
    }

    public void setThingTime(Date thingTime) {
        this.thingTime = thingTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getShareCount() {
        return shareCount;
    }

    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
    }

    public Long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Long commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Long getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Long attentionCount) {
        this.attentionCount = attentionCount;
    }

    @Transient
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<Words> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<Words> wordsList) {
        this.wordsList = wordsList;
    }

    @Override
    public String toString() {
        return "LFMessage{" +
                "id=" + id +
                ", isDelete=" + isDelete +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", title='" + title + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", kind='" + kind + '\'' +
                ", detail='" + detail + '\'' +
                ", thingTime=" + thingTime +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", shareCount=" + shareCount +
                ", commentsCount=" + commentsCount +
                ", attentionCount=" + attentionCount +
                ", user=" + user +
                ", imageUrls=" + imageUrls +
                ", wordsList=" + wordsList +
                '}';
    }
}
