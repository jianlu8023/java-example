package com.github.jianlu8023.example.integration.middleware.cache.entity;

import java.util.*;

public class CaffeineUser {

    private String id;
    private String name;
    private Integer age;
    private String address;
    private String email;
    private String phone;
    private String sex;

    public CaffeineUser() {
    }

    public CaffeineUser(String id, String name, Integer age, String address, String email, String phone, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaffeineUser that = (CaffeineUser) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(age, that.age) && Objects.equals(address, that.address) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(sex, that.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, address, email, phone, sex);
    }

    @Override
    public String toString() {
        return "CaffeineUser{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", address='" + address + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", sex='" + sex + '\'' + '}';
    }
}
