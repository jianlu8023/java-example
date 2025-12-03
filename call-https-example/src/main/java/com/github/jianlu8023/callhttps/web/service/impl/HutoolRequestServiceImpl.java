package com.github.jianlu8023.callhttps.web.service.impl;

import cn.hutool.http.*;
import com.github.jianlu8023.callhttps.utils.*;
import com.github.jianlu8023.callhttps.web.service.*;
import org.slf4j.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

@Component
public class HutoolRequestServiceImpl implements RequestService {

    private static final Logger L = LoggerFactory.getLogger(HutoolRequestServiceImpl.class);

    @Scheduled(cron = "*/15 * * * * *")
    @Override
    public void SendHttpsRequest() {
        HttpResponse execute = HttpRequest.post("https://localhost:8090/https/https/rec")
                .header("Content-Type", "application/json;charset=utf-8")
                .setSSLSocketFactory(SSLUtils.createSSLContext().getSocketFactory())
                .execute();
        final String body = execute.body();
        L.info("Response :: {}", body);
    }
}
