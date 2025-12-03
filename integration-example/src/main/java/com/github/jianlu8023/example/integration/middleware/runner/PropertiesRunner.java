package com.github.jianlu8023.example.integration.middleware.runner;


import com.alibaba.fastjson.*;
import com.github.jianlu8023.example.integration.middleware.properties.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;

@Component
public class PropertiesRunner implements ApplicationRunner {
    private static final Logger L = LoggerFactory.getLogger(PropertiesRunner.class);


    private DemoYamlProperties demo;

    @Autowired
    public void setDemo(DemoYamlProperties demo) {
        this.demo = demo;
    }

    private DemoPropertyProperties pro;

    @Autowired
    public void setPro(DemoPropertyProperties pro) {
        this.pro = pro;
    }

    @Override
    public void run(ApplicationArguments args) {
        L.debug("加载自定义yaml配置文件...");
        L.debug("demo {}", JSONObject.toJSONString(demo));
        L.debug("pro {}", JSONObject.toJSONString(pro));
    }
}
