package com.github.jianlu8023.grpcexample.server;


import com.github.jianlu8023.grpcexample.server.grpc.annotation.*;
import com.github.jianlu8023.grpcexample.server.grpc.config.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.*;

import java.io.*;
import java.util.*;


@SpringBootApplication
public class GrpcServerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        // SpringApplication.run(GrpcServerApplication.class, args);
        // 启动SpringBoot web
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(GrpcServerApplication.class, args);
        Map<String, Object> grpcServiceBeanMap = configurableApplicationContext.getBeansWithAnnotation(GrpcService.class);
        GrpcServerManager serviceManager = configurableApplicationContext.getBean(GrpcServerManager.class);
        serviceManager.loadService(grpcServiceBeanMap);
    }
}
