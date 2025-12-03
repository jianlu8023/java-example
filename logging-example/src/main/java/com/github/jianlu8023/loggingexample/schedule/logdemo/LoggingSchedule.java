package com.github.jianlu8023.loggingexample.schedule.logdemo;

import org.slf4j.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

@Component
public class LoggingSchedule {

    private static final Logger L = LoggerFactory.getLogger(LoggingSchedule.class);

    @Scheduled(cron = "0/3 * * * * *")
    public void loggingDemo() {
        L.info("info testing...");
        L.debug("debug testing...");
        L.warn("warn testing...");
        L.trace("trace testing...");
        L.error("error testing...");
    }
}
