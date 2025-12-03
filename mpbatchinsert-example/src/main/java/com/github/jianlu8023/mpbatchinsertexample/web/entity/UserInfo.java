package com.github.jianlu8023.mpbatchinsertexample.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import net.datafaker.*;

import java.io.*;
import java.util.*;

/**
 * 用户信息表
 *
 * @TableName user_info
 */
@TableName(value = "user_info")
public class UserInfo implements Serializable {
    /**
     * 主键id 自增 int类型
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     *
     */
    @TableField(value = "user_name")
    private String user_name;

    /**
     * 用户年龄
     */
    @TableField(value = "user_age")
    private Integer user_age;

    /**
     * 用户身份证
     */
    @TableField(value = "user_sfz")
    private Integer user_sfz;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键id 自增 int类型
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 主键id 自增 int类型
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     *
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     *
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * 用户年龄
     */
    public Integer getUser_age() {
        return user_age;
    }

    /**
     * 用户年龄
     */
    public void setUser_age(Integer user_age) {
        this.user_age = user_age;
    }

    /**
     * 用户身份证
     */
    public Integer getUser_sfz() {
        return user_sfz;
    }

    /**
     * 用户身份证
     */
    public void setUser_sfz(Integer user_sfz) {
        this.user_sfz = user_sfz;
    }

    private static final Faker faker = new Faker();

    public static List<UserInfo> createTestData(Integer size) {

        List<UserInfo> result = new ArrayList<>();
        UserInfo userInfo;
        if ((0 == size) || (null == size)) {
            for (int i = 0; i < 10; i++) {
                userInfo = new UserInfo();
                userInfo.setUser_age(faker.number().numberBetween(1, 99));
                userInfo.setUser_name(faker.name().name());
                userInfo.setUser_sfz(Integer.parseInt(faker.regexify("(\\d{7})|(\\d{6}([0-9]))")));

                result.add(userInfo);
            }
        } else {
            for (int i = 0; i < size; i++) {

                userInfo = new UserInfo();
                userInfo.setUser_age(faker.number().numberBetween(1, 99));
                userInfo.setUser_name(faker.name().name());
                userInfo.setUser_sfz(Integer.parseInt(faker.regexify("(\\d{7})|(\\d{6}([0-9]))")));

                result.add(userInfo);
            }
        }
        return result;
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
        UserInfo other = (UserInfo) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
                && (this.getUser_name() == null ? other.getUser_name() == null : this.getUser_name().equals(other.getUser_name()))
                && (this.getUser_age() == null ? other.getUser_age() == null : this.getUser_age().equals(other.getUser_age()))
                && (this.getUser_sfz() == null ? other.getUser_sfz() == null : this.getUser_sfz().equals(other.getUser_sfz()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getUser_name() == null) ? 0 : getUser_name().hashCode());
        result = prime * result + ((getUser_age() == null) ? 0 : getUser_age().hashCode());
        result = prime * result + ((getUser_sfz() == null) ? 0 : getUser_sfz().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", user_name=").append(user_name);
        sb.append(", user_age=").append(user_age);
        sb.append(", user_sfz=").append(user_sfz);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}