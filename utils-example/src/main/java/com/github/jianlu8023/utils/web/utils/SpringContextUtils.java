package com.github.jianlu8023.utils.web.utils;

import org.slf4j.*;
import org.springframework.beans.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.lang.annotation.*;
import java.util.*;

@Component
public class SpringContextUtils implements ApplicationContextAware, Closeable {

    private static final Logger L = LoggerFactory.getLogger(SpringContextUtils.class);

    /**
     * 上下文对象
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtils.applicationContext == null) {
            L.debug("当前上下文对象为空，进行设置山下文");
            SpringContextUtils.applicationContext = applicationContext;
        }
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    private static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 清除applicationContext静态变量.
     */
    public static void cleanApplicationContext() {
        applicationContext = null;
    }


    public static Object getBean(String clazz) throws BeansException {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String s, Class<T> clazz) throws BeansException {
        return getApplicationContext().getBean(s, clazz);
    }

    public static Object getBean(String s, Object... objects) throws BeansException {
        return getApplicationContext().getBean(s, objects);
    }

    public static <T> T getBean(Class<T> tClass) throws BeansException {
        return getApplicationContext().getBean(tClass);
    }

    public static <T> T getBean(Class<T> clazz, Object... objects) throws BeansException {
        return getApplicationContext().getBean(clazz, objects);
    }


    @SuppressWarnings("unused")
    public static <T> Map<String, T> getBeans(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> clazz) throws BeansException {
        return getApplicationContext().getBeansWithAnnotation(clazz);
    }

    /**
     * 获取配置文件配置项的值
     *
     * @param key 配置项key
     */
    @SuppressWarnings("unused")
    private static String getEnvironmentProperty(String key) {
        return getApplicationContext().getEnvironment().getProperty(key);
    }

    /**
     * 获取spring.profiles.active
     */
    @SuppressWarnings("unused")
    private static String[] getActiveProfile() {
        return getApplicationContext().getEnvironment().getActiveProfiles();
    }


    @Override
    public void close() {
        cleanApplicationContext();
    }
}
