package com.github.jianlu8023.example.integration.web.database.mysql.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.*;
import java.util.*;

@TableName(value = "tb_user")
public class TbUser implements Serializable {

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "full_name")
    private String full_name;

    @TableField(value = "email")
    private String email;

    @TableField(value = "phone_number")
    private String phone_number;

    @TableField(value = "gender")
    private String gender;

    @TableField(value = "birth_date")
    private Date birth_date;

    @TableField(value = "address")
    private String address;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }


    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone_number() {
        return phone_number;
    }


    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Date getBirth_date() {
        return birth_date;
    }


    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
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
        TbUser other = (TbUser) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
                && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
                && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
                && (this.getFull_name() == null ? other.getFull_name() == null : this.getFull_name().equals(other.getFull_name()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getPhone_number() == null ? other.getPhone_number() == null : this.getPhone_number().equals(other.getPhone_number()))
                && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
                && (this.getBirth_date() == null ? other.getBirth_date() == null : this.getBirth_date().equals(other.getBirth_date()))
                && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getFull_name() == null) ? 0 : getFull_name().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPhone_number() == null) ? 0 : getPhone_number().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getBirth_date() == null) ? 0 : getBirth_date().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", full_name=").append(full_name);
        sb.append(", email=").append(email);
        sb.append(", phone_number=").append(phone_number);
        sb.append(", gender=").append(gender);
        sb.append(", birth_date=").append(birth_date);
        sb.append(", address=").append(address);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}