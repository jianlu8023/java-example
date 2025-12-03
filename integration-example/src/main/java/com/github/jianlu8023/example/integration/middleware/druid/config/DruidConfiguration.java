package com.github.jianlu8023.example.integration.middleware.druid.config;


import com.alibaba.druid.support.http.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.web.servlet.*;
import org.springframework.context.annotation.*;

import javax.servlet.*;

@Configuration
public class DruidConfiguration {

    /**
     * 配置Druid 监控启动页面
     *
     * @return servletRegistrationBean
     */
    @Bean
    @ConditionalOnMissingBean
    public ServletRegistrationBean<Servlet> druidStartViewServlet() {
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 白名单
        servletRegistrationBean.addInitParameter("allow", "0.0.0.0");
        // 黑名单
        // servletRegistrationBean.addInitParameter("deny", "");
        // 登录查看信息的账密，用于登录Druid监控后台
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "adminpw");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "true");
        return servletRegistrationBean;
    }

    /**
     * Druid监控过滤器配置规则
     * ConditionalOnMissingBean 防止注册相同的bean
     *
     * @return filterFilterRegistrationBean
     */
    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new WebStatFilter());
        // 添加过滤规则
        filterFilterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息
        filterFilterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterFilterRegistrationBean;
    }
}
