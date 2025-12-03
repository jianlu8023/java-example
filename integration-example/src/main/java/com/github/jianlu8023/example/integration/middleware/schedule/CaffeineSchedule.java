package com.github.jianlu8023.example.integration.middleware.schedule;

import com.github.jianlu8023.example.integration.middleware.cache.entity.*;
import com.github.jianlu8023.example.integration.middleware.cache.service.*;
import com.github.jianlu8023.mock.generator.pojo.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class CaffeineSchedule {
    private static final Logger L = LoggerFactory.getLogger(CaffeineSchedule.class);

    private static final List<String> ids = new ArrayList<>();

    private CaffeineService caffeineService;
    private PojoGenerator<CaffeineUser> pojoGenerator;

    @Autowired
    public void setCaffeineService(CaffeineService caffeineService) {
        this.caffeineService = caffeineService;
    }

    @Autowired
    public void setPojoGenerator(PojoGenerator<CaffeineUser> pojoGenerator) {
        this.pojoGenerator = pojoGenerator;
    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void save() {
        CaffeineUser user = pojoGenerator.generate(CaffeineUser.class);
        caffeineService.save(user);
        ids.add(user.getId());
    }

    @Scheduled(cron = "*/20 * * * * ?")
    public void get() {
        if (ids.isEmpty()) {
            return;
        }
        ids.forEach(id -> {
            CaffeineUser caffeineUser = caffeineService.get(String.valueOf(id));
            L.info("id: {}, caffeineUser: {}", id, caffeineUser);
        });
    }

    @Scheduled(cron = "*/30 * * * * ?")
    public void delete() {
        if (ids.isEmpty()) {
            return;
        }
        String deleteId = ids.get(0);
        L.debug("delete id: {}", deleteId);
        caffeineService.delete(String.valueOf(deleteId));
        ids.remove(0);
    }


}
