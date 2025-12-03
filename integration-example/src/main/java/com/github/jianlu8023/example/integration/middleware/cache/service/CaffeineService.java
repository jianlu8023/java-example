package com.github.jianlu8023.example.integration.middleware.cache.service;

import com.github.jianlu8023.example.integration.middleware.cache.entity.*;

public interface CaffeineService {


    void save(CaffeineUser user);

    CaffeineUser get(String id);

    void delete(String id);

    void update(CaffeineUser user);
}
