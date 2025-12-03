package com.github.jianlu8023.example.integration.middleware.schedule;


import com.github.jianlu8023.example.integration.middleware.utils.*;
import okhttp3.*;
import org.slf4j.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

@Component
public class HttpsRequestExampleSchedule {
    private static final Logger L = LoggerFactory.getLogger(HttpsRequestExampleSchedule.class);

    @Scheduled(cron = "0/30 * * * * ?") // 每5秒执行一次
    public void httpsRequest() {
        Response response = null;
        try {
            OkHttpClient build = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .sslSocketFactory(SSLUtils.createSSLContext().getSocketFactory(),
                            (X509TrustManager) SSLUtils.getTrustAllCerts()[0])
                    .hostnameVerifier((hostname, session) -> true)
                    .build();
            final String ip = NetInterfaceUtils.getRealInter().getIp();
            final String unique32 = NetInterfaceUtils.getRealInter().getUnique32();
            final MultipartBody requestBody = new MultipartBody.Builder()
                    .addFormDataPart("ip", ip)
                    .addFormDataPart("mac", unique32)
                    .build();
            Request request = new Request.Builder()
                    .url("https://localhost:8090/integration/https/rec")
                    .post(requestBody)
                    .build();

            response = build.newCall(request).execute();
            if (response.isSuccessful()) {
                final String body = Objects.requireNonNull(response.body()).string();
                L.info("Response :: {}", body);
            } else {
                L.info("Response code {}", response.code());
            }
        } catch (SocketException | UnknownHostException e) {
            L.error("获取本机IP失败 \n", e);
        } catch (IOException e) {
            L.error("SendHttpsRequest error \n", e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                response.close();
            }
        }

    }

}
