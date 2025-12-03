package com.github.jianlu8023.example.integration.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.jianlu8023.example.integration.web.database.mysql.entity.*;
import com.github.jianlu8023.example.integration.web.database.mysql.service.*;

import com.github.jianlu8023.format.response.*;
import com.github.jianlu8023.format.response.ResponseStatus;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger L = LoggerFactory.getLogger(UserController.class);

    private TbUserService tbUserService;

    @Autowired
    public void setTbUserService(TbUserService tbUserService) {
        this.tbUserService = tbUserService;
    }


    @RequestMapping(value = "/getOne", method = {
            RequestMethod.GET, RequestMethod.POST
    })
    public ApiResponse<TbUser> getOne() {
        LambdaQueryWrapper<TbUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(TbUser::getUid);
        final TbUser one = tbUserService.getOne(queryWrapper);
        return ApiResponse.success(ResponseStatus.SUCCESS, one);
    }


    @RequestMapping(value = "/addOne", method = {
            RequestMethod.GET, RequestMethod.POST
    })
    public ApiResponse<Object> addOne() {
        final TbUser tbUser = tbUserService.create();
        return ApiResponse.success(ResponseStatus.SUCCESS, tbUser);
    }

    @RequestMapping(value = "/list", method = {
            RequestMethod.GET, RequestMethod.POST
    })
    public ApiResponse<Object> list(
            @RequestParam("pageNo") Integer pageNo,
            @RequestParam("pageSize") Integer pageSize) {
        List<TbUser> list = tbUserService.list(pageNo, pageSize);
        return ApiResponse.success(ResponseStatus.SUCCESS, list);
    }

}
