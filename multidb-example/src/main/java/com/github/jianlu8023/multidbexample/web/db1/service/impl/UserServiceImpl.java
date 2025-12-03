package com.github.jianlu8023.multidbexample.web.db1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jianlu8023.multidbexample.web.db1.entity.User;
import com.github.jianlu8023.multidbexample.web.db1.service.UserService;
import com.github.jianlu8023.multidbexample.web.db1.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author root
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2024-04-15 16:46:58
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Override
    public Integer insertBatch(Collection<User> insert) {
        return this.baseMapper.insertBatchSomeColumn(insert);
    }

}




