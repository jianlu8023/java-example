package com.github.jianlu8023.example.integration.middleware.properties;


import com.github.jianlu8023.example.integration.middleware.yaml.factory.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
@ConfigurationProperties(prefix = "demo")
@PropertySources({
        @PropertySource(factory = YamlPropertySourceFactory.class,
                value = "classpath:demo.yaml",
                encoding = "UTF-8")
})
public class DemoYamlProperties {
    private String name;
    private Integer age;
    private String gender;


    public DemoYamlProperties() {
    }

    public DemoYamlProperties(String name, Integer age, String gender) {
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
        DemoYamlProperties that = (DemoYamlProperties) o;
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
