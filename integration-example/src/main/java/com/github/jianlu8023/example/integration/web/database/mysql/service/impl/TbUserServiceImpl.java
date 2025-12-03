package com.github.jianlu8023.example.integration.web.database.mysql.service.impl;

import com.baomidou.dynamic.datasource.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.baomidou.mybatisplus.extension.service.impl.*;
import com.github.jianlu8023.example.integration.middleware.exceptions.*;
import com.github.jianlu8023.example.integration.web.database.mysql.entity.*;
import com.github.jianlu8023.example.integration.web.database.mysql.mapper.*;
import com.github.jianlu8023.example.integration.web.database.mysql.service.*;

import com.github.jianlu8023.mock.generator.pojo.*;
import com.github.pagehelper.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;


@Service
@DS("mysql")
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser>
        implements TbUserService {
    private static final Logger L = LoggerFactory.getLogger(TbUserServiceImpl.class);

    private PojoGenerator<TbUser> generator;

    @Autowired
    public void setGenerator(PojoGenerator<TbUser> generator) {
        this.generator = generator;
    }

    @Override
    @DS("mysql")
    public TbUser create() {
        final TbUser generate = generator.generate(TbUser.class);
        final boolean save = save(generate);
        if (save) {
            return generate;
        } else {
            throw new CreateFailException("create fail");
        }
    }

    @Override
    public List<TbUser> list(Integer pageNo, Integer pageSize) {

        // param1  pageNum:页码
        // param2 pageSize:每页显示数量
        // param3 count:是否进行count查询
        // param4 reasonable:分页合理化,null时用默认配置
        // param5 pageSizeZero:true且pageSize=0时返回全部结果，false时分页,null时用默认配置
        PageHelper.startPage(pageNo, pageSize, true, true, true);
        LambdaQueryWrapper<TbUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(TbUser::getUid);
        return list(queryWrapper);
    }
}




