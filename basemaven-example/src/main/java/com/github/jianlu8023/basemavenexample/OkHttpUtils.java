package com.github.jianlu8023.basemavenexample;

import okhttp3.*;

import java.util.concurrent.*;

public class OkHttpUtils {

    private final static int READ_TIMEOUT = 100;

    private final static int CONNECT_TIMEOUT = 60;

    private final static int WRITE_TIMEOUT = 60;

    private static volatile OkHttpClient okHttpClient;

    private OkHttpUtils() {
    }

    private static void create() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        //读取超时
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        //连接超时
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //写入超时
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        //自定义连接池最大空闲连接数和等待时间大小，否则默认最大5个空闲连接
        clientBuilder.connectionPool(new ConnectionPool(32, 5, TimeUnit.MINUTES));

        okHttpClient = clientBuilder.build();
    }

    public static OkHttpClient getInstance() {
        if (null == okHttpClient) {
            synchronized (OkHttpUtils.class) {
                if (okHttpClient == null) {
                    create();
                    return okHttpClient;
                }
            }
        }
        return okHttpClient;
    }
}