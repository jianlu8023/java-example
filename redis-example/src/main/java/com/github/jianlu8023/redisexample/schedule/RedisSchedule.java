package com.github.jianlu8023.redisexample.schedule;

import com.github.jianlu8023.redisexample.redis.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;
import org.slf4j.*;

import java.util.*;

@Component
public class RedisSchedule {
    private static final Logger L = LoggerFactory.getLogger(RedisSchedule.class);

    private RedisTemplateUtils redisTemplateUtils;

    @Autowired
    public void setRedisTemplateUtils(RedisTemplateUtils redisTemplateUtils) {
        this.redisTemplateUtils = redisTemplateUtils;
    }

    private List<String> keys = new ArrayList<>();

    @Scheduled(cron = "*/30 * * * * *")
    void read() {
        keys.forEach(key -> {
            String value = redisTemplateUtils.get(key);
            L.info("from redis read key {} value {}", key, value);
        });
    }

    @Scheduled(cron = "*/10 * * * * *")
    void write() {
        String key = UUID.randomUUID().toString();
        L.info("set key {} to redis", key);
        redisTemplateUtils.set(key, UUID.randomUUID().toString(), 600L);
        keys.add(key);
    }

}


