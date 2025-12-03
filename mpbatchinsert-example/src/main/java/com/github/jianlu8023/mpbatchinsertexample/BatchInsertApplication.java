package com.github.jianlu8023.mpbatchinsertexample;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.transaction.annotation.*;

@SpringBootApplication
@EnableTransactionManagement
public class BatchInsertApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchInsertApplication.class, args);
    }
}
