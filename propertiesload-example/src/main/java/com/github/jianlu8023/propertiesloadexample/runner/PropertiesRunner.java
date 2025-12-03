package com.github.jianlu8023.propertiesloadexample.runner;


import com.github.jianlu8023.propertiesloadexample.properties.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;

@Component
public class PropertiesRunner implements ApplicationRunner {

    private DemoProperties demo;

    @Autowired
    public void setDemo(DemoProperties demo) {
        this.demo = demo;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("demo properties runner");
        System.out.println(demo);
    }
}
