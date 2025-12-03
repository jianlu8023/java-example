package com.github.jianlu8023.callhttps.web.service.impl;

import com.github.jianlu8023.callhttps.utils.*;
import com.github.jianlu8023.callhttps.web.service.*;
import org.apache.http.impl.client.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.http.client.*;
import org.springframework.web.client.*;

import java.util.*;

// @Component
public class HttpRequestServiceImpl implements RequestService {

    private static final Logger L = LoggerFactory.getLogger(HttpRequestServiceImpl.class);

    // @Scheduled(cron = "*/10 * * * * *")
    @Override
    public void SendHttpsRequest() {
        try {
            // 创建基于 HttpClient 的请求工厂，并设置 SSLContext
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
                    HttpClients.custom().setSSLContext(SSLUtils.createSSLContext()).build()
            );

            // 创建 RestTemplate 实例
            RestTemplate restTemplate = new RestTemplate(requestFactory);

            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            // 创建请求实体
            HttpEntity<String> requestEntity = new HttpEntity<>("", headers);

            // 发送 POST 请求
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://localhost:8090/https/https/rec",
                    HttpMethod.POST,
                    requestEntity,
                    String.class,
                    Collections.emptyMap());

            // 打印响应体
            System.out.println("Response :: " + response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
