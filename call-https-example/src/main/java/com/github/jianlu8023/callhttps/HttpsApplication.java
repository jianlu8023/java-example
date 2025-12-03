package com.github.jianlu8023.callhttps;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.scheduling.annotation.*;

@SpringBootApplication
@EnableScheduling
public class HttpsApplication {
    public static void main(String[] args) {
        SpringApplication.run(HttpsApplication.class, args);
    }
}
