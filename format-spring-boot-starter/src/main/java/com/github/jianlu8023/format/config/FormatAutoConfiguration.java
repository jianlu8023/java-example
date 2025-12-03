package com.github.jianlu8023.format.config;

import com.github.jianlu8023.format.advice.exception.*;
import com.github.jianlu8023.format.advice.request.log.*;
import com.github.jianlu8023.format.advice.response.*;
import lombok.extern.slf4j.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;

@Configuration
@Slf4j
public class FormatAutoConfiguration {

    @Bean
    @ConditionalOnClass(GlobalExceptionAdvice.class)
    GlobalExceptionAdvice globalExceptionHandler() {
        log.debug("inject globalExceptionHandler");
        return new GlobalExceptionAdvice();
    }

    @Bean
    @ConditionalOnClass(ResponseBodyResultAdvice.class)
    ResponseBodyResultAdvice responseBodyResultAdvice() {
        log.debug("inject responseBodyResultAdvice");
        return new ResponseBodyResultAdvice();
    }

    @Bean
    @ConditionalOnClass(RequestLogAdvice.class)
    RequestLogAdvice requestLogAdvice() {
        log.debug("inject requestLogAdvice");
        return new RequestLogAdvice();
    }
}
