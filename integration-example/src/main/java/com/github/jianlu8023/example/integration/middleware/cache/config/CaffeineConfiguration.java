package com.github.jianlu8023.example.integration.middleware.cache.config;

import com.github.benmanes.caffeine.cache.*;
import org.springframework.cache.*;
import org.springframework.cache.caffeine.*;
import org.springframework.context.annotation.*;

import java.util.concurrent.*;

@Configuration
public class CaffeineConfiguration {

    @Bean("caffeineCacheManager")
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder().initialCapacity(100).maximumSize(500).expireAfterWrite(10, TimeUnit.SECONDS));
        return cacheManager;
    }

}
