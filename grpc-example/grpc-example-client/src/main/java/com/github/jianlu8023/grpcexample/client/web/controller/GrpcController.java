package com.github.jianlu8023.grpcexample.client.web.controller;

import com.github.jianlu8023.grpcexample.client.web.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/grpc")
@RestController
public class GrpcController {

    private GrpcService grpcService;

    @Autowired
    public void setGrpcService(GrpcService grpcService) {
        this.grpcService = grpcService;
    }

    @RequestMapping("/ping")
    public Object ping() throws Exception {
        grpcService.ping();
        return "";
    }
}
