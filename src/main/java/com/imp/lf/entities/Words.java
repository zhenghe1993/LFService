package com.imp.lf.entities;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/19
 */
@TableName("t_words")
public class Words implements Serializable{

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "is_delete")
    private int isDelete;

    private String type;//LF 失物招领   DIS发现  LEA留言

    private String kind;//TOW 评论   TOM 评论 message数据

    @TableField(value = "from_user_id")
    private Long fromUserId;
    @TableField(value = "to_user_id")
    private Long toUserId;
    @TableField(value = "create_time")
    private Date createTime;
    private String detail;
    @TableField(value = "data_id")
    private Long dataId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    @Override
    public String toString() {
        return "Words{" +
                "id=" + id +
                ", isDelete=" + isDelete +
                ", type='" + type + '\'' +
                ", kind='" + kind + '\'' +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", createTime=" + createTime +
                ", detail='" + detail + '\'' +
                ", dataId=" + dataId +
                '}';
    }
}
