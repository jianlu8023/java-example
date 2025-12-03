package com.github.jianlu8023.grpcexample.server.grpc.service;


import com.github.jianlu8023.grpcexample.server.grpc.annotation.*;
import com.github.jianlu8023.grpcexample.proto.hello.*;
import io.grpc.stub.*;

@GrpcService
public class HelloGrpcService extends HelloServiceGrpc.HelloServiceImplBase {
    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        // super.hello(request, responseObserver);
        System.out.println(request);
        HelloResponse response = HelloResponse.newBuilder()
                .setResult("hello" + request.getMsg())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
