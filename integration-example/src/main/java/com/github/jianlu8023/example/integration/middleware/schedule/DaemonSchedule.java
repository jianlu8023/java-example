package com.github.jianlu8023.example.integration.middleware.schedule;

import com.github.jianlu8023.example.integration.middleware.properties.*;
import com.github.jianlu8023.example.integration.middleware.utils.*;
import okhttp3.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

@Component
public class DaemonSchedule {
    private static final Logger L = LoggerFactory.getLogger(DaemonSchedule.class);

    private PushProperties push;

    @Autowired
    public void setPush(PushProperties push) {
        this.push = push;
    }

    @Scheduled(cron = "*/15 * * * * *")
    public void daemon() {
        L.debug("service daemon ...");
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void pushStatus() {
        if (!push.getStatus().getEnabled()) {
            L.debug("push status disabled");
            return;
        }
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
            final RequestBody requestBody = RequestBody.create("", MediaType.parse("application/json; charset=utf-8"));
            Request request = new Request.Builder()
                    .url(push.getStatus().getUrl())
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
