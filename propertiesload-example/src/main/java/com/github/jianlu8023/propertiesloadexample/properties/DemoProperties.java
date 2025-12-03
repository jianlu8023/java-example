package com.github.jianlu8023.propertiesloadexample.properties;


import com.github.jianlu8023.propertiesloadexample.config.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
@ConfigurationProperties(prefix = "demo")
@PropertySource(factory = YamlPropertySourceFactory.class,
        value = "classpath:demo.yaml",
        encoding = "UTF-8")
public class DemoProperties {
    private String name;
    private Integer age;
    private String gender;


    public DemoProperties() {
    }

    public DemoProperties(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemoProperties that = (DemoProperties) o;
        return Objects.equals(name, that.name) && Objects.equals(age, that.age) && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, gender);
    }

    @Override
    public String toString() {
        return "DemoProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
