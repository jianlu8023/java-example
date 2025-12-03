package com.github.jianlu8023.callhttps.web.service.impl;


import com.github.jianlu8023.callhttps.utils.*;
import com.github.jianlu8023.callhttps.web.service.*;
import okhttp3.*;
import org.slf4j.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import javax.net.ssl.*;
import java.io.*;
import java.util.concurrent.*;

@Component
public class OkHttp3RequestServiceImpl implements RequestService {

    private static final Logger L = LoggerFactory.getLogger(OkHttp3RequestServiceImpl.class);


    @Scheduled(cron = "*/20 * * * * *")
    @Override
    public void SendHttpsRequest() {
        OkHttpClient build = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .sslSocketFactory(SSLUtils.createSSLContext().getSocketFactory(), (X509TrustManager) SSLUtils.getTrustAllCerts()[0])
                .hostnameVerifier((hostname, session) -> true)
                .build();
        RequestBody requestBody = RequestBody
                .create(MediaType.parse("application/json;charset=utf-8"), "");
        Request request = new Request.Builder()
                .url("https://localhost:8090/https/https/rec")
                .post(requestBody)
                .build();
        try (Response response = build.newCall(request).execute()) {
            if (response.isSuccessful()) {
                final String body = response.body().string();
                L.info("Response :: {}", body);
            } else {
                L.info("Response code {}", response.code());
            }
        } catch (IOException e) {
            L.error("SendHttpsRequest error \n", e);
        }


    }
}
