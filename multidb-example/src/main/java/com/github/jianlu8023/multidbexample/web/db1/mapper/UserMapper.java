package com.github.jianlu8023.multidbexample.web.db1.mapper;

import com.github.jianlu8023.multidbexample.mybatisplus.mapper.*;
import com.github.jianlu8023.multidbexample.web.db1.entity.*;
import org.apache.ibatis.annotations.*;

/**
 * @author root
 * @description 针对表【user(用户信息表)】的数据库操作Mapper
 * @createDate 2024-04-15 16:46:58
 * @Entity com.github.jianlu8023.multidbexample.web.db1.entity.User
 */
@Mapper
public interface UserMapper extends BatchInsertBaseMapper<User> {

}




