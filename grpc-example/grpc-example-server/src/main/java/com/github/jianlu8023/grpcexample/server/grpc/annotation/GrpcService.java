package com.github.jianlu8023.grpcexample.server.grpc.annotation;

import org.springframework.stereotype.*;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface GrpcService {
}
