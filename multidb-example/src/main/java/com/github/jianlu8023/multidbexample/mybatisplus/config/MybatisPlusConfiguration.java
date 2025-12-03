package com.github.jianlu8023.multidbexample.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.*;
import com.baomidou.mybatisplus.extension.plugins.inner.*;
import com.github.jianlu8023.multidbexample.mybatisplus.injector.*;
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
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }


}
