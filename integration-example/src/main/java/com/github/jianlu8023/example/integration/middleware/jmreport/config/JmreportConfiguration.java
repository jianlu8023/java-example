package com.github.jianlu8023.example.integration.middleware.jmreport.config;

import org.springframework.boot.context.properties.*;
import org.springframework.boot.jdbc.*;
import org.springframework.context.annotation.*;

import javax.sql.*;

// @Configuration
public class JmreportConfiguration {

    /**
     * 1、bean的名称必须为minidaoDataSource，否则不生效
     * 2、jeecg.minidao-datasource对应的是yml中的jeecg下的minidao-datasource，可自定义
     */
    @Bean(name = "minidaoDataSource")
    @ConfigurationProperties(prefix = "jeecg.minidao-datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


}
