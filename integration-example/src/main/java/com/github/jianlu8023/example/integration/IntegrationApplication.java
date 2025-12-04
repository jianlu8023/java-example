package com.github.jianlu8023.example.integration;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.cache.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.transaction.annotation.*;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}
        // , scanBasePackages = {"org.jeecg"}
        // , scanBasePackages = {"org.jeecg.modules.jmreport"}
)
@EnableScheduling
@EnableTransactionManagement
@EnableCaching
public class IntegrationApplication {
    private static final Logger L = LoggerFactory.getLogger(IntegrationApplication.class);

    static {
        // 设置property 关闭 pagehelper 的banner
        // L.debug("pagehelper banner is disabled");
        System.setProperty("pagehelper.banner", "false");
        System.setProperty("druid.mysql.usePingMethod", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(IntegrationApplication.class, args);
    }

}
