package com.github.jianlu8023.multidbexample.pagehelper.dialect;

import com.github.pagehelper.page.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;

@Component
public class DialectInit implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        PageAutoDialect.registerDialectAlias("mysql", LocalMysqlDialect.class);
    }
}
