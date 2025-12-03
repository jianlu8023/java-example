package com.github.jianlu8023.mpbatchinsertexample.web.service;

import com.baomidou.mybatisplus.core.conditions.*;
import com.baomidou.mybatisplus.extension.service.*;
import com.github.jianlu8023.mpbatchinsertexample.web.entity.*;

import java.util.*;
import java.util.function.*;

/**
 * @author root
 * @description 针对表【user_info(用户信息表)】的数据库操作Service
 * @createDate 2024-04-07 17:17:16
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * saveOrUpdateBatch By custom rules
     *
     * @param entityList
     * @param function
     * @return
     */
    Boolean saveOrUpdateBatch(Collection<UserInfo> entityList, Function<UserInfo, Wrapper<UserInfo>> function);
}
