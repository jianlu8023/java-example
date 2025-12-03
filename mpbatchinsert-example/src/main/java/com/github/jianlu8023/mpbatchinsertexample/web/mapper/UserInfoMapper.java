package com.github.jianlu8023.mpbatchinsertexample.web.mapper;

import com.github.jianlu8023.mpbatchinsertexample.mybatisplus.mapper.*;
import com.github.jianlu8023.mpbatchinsertexample.web.entity.*;
import org.apache.ibatis.annotations.*;

/**
 * @author root
 * @description 针对表【user_info(用户信息表)】的数据库操作Mapper
 * @createDate 2024-04-07 17:17:16
 * @Entity com.github.jianlu8023.mpbatchinsertexample.web.entity.UserInfo
 */
@Mapper
public interface UserInfoMapper extends BatchInsertBaseMapper<UserInfo> {

}




