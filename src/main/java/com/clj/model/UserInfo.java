package com.clj.model;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    private Integer id;
    private String birthday;
    private String gender;
    private String avatar;
    private String createTime;
    private String updateTime;
    private int userId;

    public UserInfo() {
    }

    public UserInfo(Integer id, String birthday, String gender, String avatar, String createTime, String updateTime, int userId) {
        this.id = id;
        this.birthday = birthday;
        this.gender = gender;
        this.avatar = avatar;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", userId=" + userId +
                '}';
    }
}
