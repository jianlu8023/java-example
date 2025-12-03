package com.github.jianlu8023.mpbatchinsertexample.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.jianlu8023.mpbatchinsertexample.web.entity.*;
import com.github.jianlu8023.mpbatchinsertexample.web.mapper.*;
import com.github.jianlu8023.mpbatchinsertexample.web.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BasicController {

    private UserInfoService userInfoService;

    private UserInfoMapper userInfoMapper;

    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    // private final EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
    //         .charset(StandardCharsets.UTF_8)
    //         .overrideDefaultInitialization(false)
    //         .ignoreRandomizationErrors(false)
    //         .scanClasspathForConcreteTypes(true)
    //         .build();

    @RequestMapping("/insertBatch")
    public Object insertBatch() {

        // UserInfo userInfo;
        // List<UserInfo> insert = new ArrayList<>();
        // for (int i = 0; i < 10; i++) {
        //     userInfo = random.nextObject(UserInfo.class);
        //     insert.add(userInfo);
        // }

        List<UserInfo> insert = UserInfo.createTestData(10);


        userInfoService.saveOrUpdateBatch(insert,
                (entity) -> new LambdaQueryWrapper<UserInfo>()
                        .eq(UserInfo::getUser_sfz, entity.getUser_sfz()));
        return "";
    }

    @RequestMapping("/insertBatchByMapper")
    public Object insertBatchByMapper() {

        // UserInfo userInfo;
        // List<UserInfo> insert = new ArrayList<>();
        // for (int i = 0; i < 10; i++) {
        //     userInfo = random.nextObject(UserInfo.class);
        //     insert.add(userInfo);
        // }

        List<UserInfo> insert = UserInfo.createTestData(10);

        userInfoMapper.insertBatchSomeColumn(insert);
        return "";
    }
}
