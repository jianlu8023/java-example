package com.github.jianlu8023.mpbatchinsertexample.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.*;
import com.baomidou.mybatisplus.core.enums.*;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.service.impl.*;
import com.baomidou.mybatisplus.extension.toolkit.*;
import com.github.jianlu8023.mpbatchinsertexample.web.entity.*;
import com.github.jianlu8023.mpbatchinsertexample.web.mapper.*;
import com.github.jianlu8023.mpbatchinsertexample.web.service.*;
import org.apache.ibatis.binding.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.function.*;

/**
 * @author root
 * @description 针对表【user_info(用户信息表)】的数据库操作Service实现
 * @createDate 2024-04-07 17:17:16
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {
    /**
     * saveOrUpdateBatch By custom rules
     *
     * @param entityList
     * @param function
     * @return
     */
    @Override
    public Boolean saveOrUpdateBatch(Collection<UserInfo> entityList, Function<UserInfo, Wrapper<UserInfo>> function) {
        return SqlHelper.saveOrUpdateBatch(
                UserInfo.class,
                UserInfoMapper.class,
                this.log,
                entityList,
                DEFAULT_BATCH_SIZE,
                ((sqlSession, entity) -> {
                    MapperMethod.ParamMap<Object> param = new MapperMethod.ParamMap<>();
                    param.put(Constants.ENTITY, entity);
                    param.put(Constants.WRAPPER, function.apply(entity));
                    return CollectionUtils.isEmpty(sqlSession.selectList(this.getSqlStatement(SqlMethod.SELECT_MAPS), param));
                })
                , ((sqlSession, entity) -> {
                    MapperMethod.ParamMap<Object> param = new MapperMethod.ParamMap<>();
                    param.put(Constants.ENTITY, entity);
                    param.put(Constants.WRAPPER, function.apply(entity));
                    sqlSession.update(this.getSqlStatement(SqlMethod.UPDATE), param);
                })
        );

    }
}




