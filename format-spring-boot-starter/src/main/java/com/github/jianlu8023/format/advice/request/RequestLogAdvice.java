package com.github.jianlu8023.format.advice.request;

import lombok.extern.slf4j.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.context.request.*;

import javax.servlet.http.*;
import java.util.*;

@Slf4j
@Aspect
@Component
public class RequestLogAdvice {

    // 为了记录执行时间 方便调试
    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    // 定义一个切入点
    @Pointcut("@annotation(com.github.jianlu8023.format.annotation.RequestLog)")
    public void pointCut() {
    }

    // 在进入方法前执行 记录请求信息
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                START_TIME.set(System.currentTimeMillis());

                log.info("Request Info - IP: {}, URL: {}, Method: {}, Controller: {}.{}(), Args: {}",
                        request.getRemoteAddr(),
                        request.getRequestURL().toString(),
                        request.getMethod(),
                        joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(),
                        Arrays.toString(joinPoint.getArgs()));
            }
        } catch (Exception e) {
            log.warn("Failed to log request info: {}", e.getMessage());
        }
    }

    // 在方法执行后执行 记录响应信息和执行时间
    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        try {
            Long startTime = START_TIME.get();
            if (startTime != null) {
                long costTime = System.currentTimeMillis() - startTime;
                START_TIME.remove(); // 清理ThreadLocal

                log.info("Response Info - Controller: {}.{}(), Cost: {} ms",
                        joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(),
                        costTime);
            }
        } catch (Exception e) {
            log.warn("Failed to log response info: {}", e.getMessage());
        }
    }

    // 环绕通知 用于捕获方法执行过程中的异常
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            log.error("Exception occurred in method: {}.{}(), Exception: {}",
                    pjp.getSignature().getDeclaringTypeName(),
                    pjp.getSignature().getName(),
                    throwable.getMessage(),
                    throwable);
            throw throwable;
        }
    }
}