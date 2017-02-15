package com.imp.lf.entities;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;


import java.io.Serializable;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/19
 */
@TableName("t_attention")
public class Attention implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String type;
    @TableField(value = "user_id")
    private Long UserId;
    @TableField(value = "data_id")
    private Long dataId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
