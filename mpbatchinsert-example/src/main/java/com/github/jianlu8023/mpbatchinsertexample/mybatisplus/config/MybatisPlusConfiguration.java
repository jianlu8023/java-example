package com.github.jianlu8023.mpbatchinsertexample.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.*;
import com.baomidou.mybatisplus.extension.plugins.inner.*;
import com.github.jianlu8023.mpbatchinsertexample.mybatisplus.injector.*;
import org.springframework.context.annotation.*;

@Configuration
public class MybatisPlusConfiguration {
    @Bean
    public BatchInsertSqlInjector batchInsertSqlInjector() {
        return new BatchInsertSqlInjector();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }


}
