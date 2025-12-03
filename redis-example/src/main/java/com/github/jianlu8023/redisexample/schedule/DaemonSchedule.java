package com.github.jianlu8023.redisexample.schedule;

import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;
import org.slf4j.*;

@Component
public class DaemonSchedule {
    private static final Logger L = LoggerFactory.getLogger(DaemonSchedule.class);

    @Scheduled(cron = "*/5 * * * * *")
    void daemon() {
        L.debug("service daemon ...");
    }

}
