package com.github.jianlu8023.callhttps.web.service.impl;

import com.github.jianlu8023.callhttps.web.service.*;
import org.slf4j.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;


@Component
public class DaemonServiceImpl implements DaemonService {
    private static final Logger L = LoggerFactory.getLogger(DaemonServiceImpl.class);


    @Scheduled(cron = "*/3 * * * * *")
    @Override
    public void daemon() {
        L.debug("service daemon ...");
    }
}
