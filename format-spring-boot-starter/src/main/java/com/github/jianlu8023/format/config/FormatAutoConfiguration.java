package com.github.jianlu8023.format.config;

import com.github.jianlu8023.format.advice.exception.*;
import com.github.jianlu8023.format.advice.request.*;
import com.github.jianlu8023.format.advice.response.*;
import lombok.extern.slf4j.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.autoconfigure.web.servlet.*;
import org.springframework.context.annotation.*;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ExceptionAdvice.class, ResponseAdvice.class, RequestLogAdvice.class})
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@Slf4j
public class FormatAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    ExceptionAdvice globalExceptionHandler() {
        log.debug("inject globalExceptionHandler");
        return new ExceptionAdvice();
    }

    @Bean
    @ConditionalOnMissingBean
    ResponseAdvice responseBodyResultAdvice() {
        log.debug("inject responseBodyResultAdvice");
        return new ResponseAdvice();
    }

    @Bean
    @ConditionalOnMissingBean
    RequestLogAdvice requestLogAdvice() {
        log.debug("inject requestLogAdvice");
        return new RequestLogAdvice();
    }
}