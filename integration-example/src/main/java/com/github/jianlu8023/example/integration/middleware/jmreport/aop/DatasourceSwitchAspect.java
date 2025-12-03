package com.github.jianlu8023.example.integration.middleware.jmreport.aop;

import com.baomidou.dynamic.datasource.toolkit.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;

// @Aspect
// @Component
// @Order(0)
public class DatasourceSwitchAspect {

    private static final Logger L = LoggerFactory.getLogger(DatasourceSwitchAspect.class);

    @Pointcut("execution(* org.jeecg.modules.drag.dao.*.*(..))")
    public void switchDatasource() {
    }

    @Around("switchDatasource()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        L.debug("切换数据源为jmreport");
        DynamicDataSourceContextHolder.push("jmreport"); // 切换数据源
        try {
            return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.poll(); // 清除上下文
        }
    }
}
