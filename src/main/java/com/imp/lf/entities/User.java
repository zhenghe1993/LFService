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
@TableName("t_user")
public class User implements Serializable {


    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String portrait;
    @TableField(value = "phone_number")
    private String phoneNumber;
    private String location;
    @TableField(value = "create_time")
    private Date createTime;
    private String password;
    private  String token;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", portrait='" + portrait + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", location='" + location + '\'' +
                ", createTime=" + createTime +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
