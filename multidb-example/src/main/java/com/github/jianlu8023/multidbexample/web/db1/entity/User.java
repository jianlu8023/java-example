package com.github.jianlu8023.multidbexample.web.db1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * @TableName user
 */
@TableName(value ="user")
public class User implements Serializable {
    /**
     * 
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    /**
     * 用户名
     */
    @TableField(value = "userName")
    private String userName;

    /**
     * 用户昵称
     */
    @TableField(value = "userNick")
    private String userNick;

    /**
     * 用户年龄
     */
    @TableField(value = "userAge")
    private Integer userAge;

    /**
     * 用户emial
     */
    @TableField(value = "userEmail")
    private String userEmail;

    /**
     * 用户性别
     */
    @TableField(value = "userGender")
    private Integer userGender;

    /**
     * 用户身份证号
     */
    @TableField(value = "userId")
    private String userId;

    /**
     * 用户创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新信息时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 用户昵称
     */
    public String getUserNick() {
        return userNick;
    }

    /**
     * 用户昵称
     */
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    /**
     * 用户年龄
     */
    public Integer getUserAge() {
        return userAge;
    }

    /**
     * 用户年龄
     */
    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    /**
     * 用户emial
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 用户emial
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 用户性别
     */
    public Integer getUserGender() {
        return userGender;
    }

    /**
     * 用户性别
     */
    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    /**
     * 用户身份证号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户身份证号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 用户创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 用户创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新信息时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新信息时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserNick() == null ? other.getUserNick() == null : this.getUserNick().equals(other.getUserNick()))
            && (this.getUserAge() == null ? other.getUserAge() == null : this.getUserAge().equals(other.getUserAge()))
            && (this.getUserEmail() == null ? other.getUserEmail() == null : this.getUserEmail().equals(other.getUserEmail()))
            && (this.getUserGender() == null ? other.getUserGender() == null : this.getUserGender().equals(other.getUserGender()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserNick() == null) ? 0 : getUserNick().hashCode());
        result = prime * result + ((getUserAge() == null) ? 0 : getUserAge().hashCode());
        result = prime * result + ((getUserEmail() == null) ? 0 : getUserEmail().hashCode());
        result = prime * result + ((getUserGender() == null) ? 0 : getUserGender().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", userName=").append(userName);
        sb.append(", userNick=").append(userNick);
        sb.append(", userAge=").append(userAge);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userGender=").append(userGender);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}