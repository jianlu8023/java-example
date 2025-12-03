package com.github.jianlu8023.multidbexample.web.db1.service;

import com.github.jianlu8023.multidbexample.web.db1.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.*;

/**
* @author root
* @description 针对表【user(用户信息表)】的数据库操作Service
* @createDate 2024-04-15 16:46:58
*/
public interface UserService extends IService<User> {


    Integer insertBatch(Collection<User> insert);
}
