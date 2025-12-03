package com.github.jianlu8023.example.integration.middleware.cache.service.impl;

import com.github.jianlu8023.example.integration.middleware.cache.entity.*;
import com.github.jianlu8023.example.integration.middleware.cache.service.*;
import org.slf4j.*;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;


@Service
@CacheConfig(cacheNames = {"caffeineCacheManager"})
public class CaffeineServiceImpl implements CaffeineService {

    private static final Logger L = LoggerFactory.getLogger(CaffeineServiceImpl.class);

    // 模拟数据
    private final Map<String, CaffeineUser> userMap = new HashMap<>(8);

    @CachePut(key = "#user.id")
    @Override
    public void save(CaffeineUser user) {
        L.debug("save user: {}", user);
        userMap.put(user.getId(), user);
        L.debug("save user {} success", user.getId());
    }

    @Cacheable(key = "#id")
    @Override
    public CaffeineUser get(String id) {
        L.debug("get user by id: {}", id);
        return userMap.get(id);
    }

    @CacheEvict(key = "#id")
    @Override
    public void delete(String id) {
        L.debug("delete user by id: {}", id);
        userMap.remove(id);
    }

    @Override
    public void update(CaffeineUser user) {
        L.debug("update user: {}", user);
        userMap.put(user.getId(), user);
    }
}
