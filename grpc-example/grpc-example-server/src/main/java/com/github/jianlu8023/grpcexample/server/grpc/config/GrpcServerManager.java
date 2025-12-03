package com.github.jianlu8023.grpcexample.server.grpc.config;

import io.grpc.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;

@Component
public class GrpcServerManager implements Closeable {
    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     *
     * <p> As noted in {@link AutoCloseable#close()}, cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing
     * the {@code IOException}.
     *
     */
    @Override
    public void close() {
        L.info("*** shutting down gRPC server since JVM is shutting down");

        if (server != null) {
            server.shutdown();
        }
        L.info("*** server shut down！！！！");
    }

    private static final Logger L = LoggerFactory.getLogger(GrpcServerManager.class);


    private Server server;

    @Value("${grpc.port:9091}")
    private Integer port;

    public void loadService(Map<String, Object> grpcServiceBeanMap) throws IOException, InterruptedException {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
        // 采用注解扫描方式，添加服务
        for (Object bean : grpcServiceBeanMap.values()) {
            serverBuilder.addService((BindableService) bean);
            L.info(bean.getClass().getSimpleName() + " is registered in Spring Boot！");
        }
        server = serverBuilder.build().start();

        L.info("grpc server is started at {}", port);

        // 增加一个钩子，当JVM进程退出时，Server 关闭
        // Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        //     System.err.println("*** shutting down gRPC server since JVM is shutting down");
        //     if (server != null) {
        //         server.shutdown();
        //     }
        //     System.err.println("*** server shut down！！！！");
        // }));
        server.awaitTermination();
    }


}
