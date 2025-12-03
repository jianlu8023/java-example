package com.github.jianlu8023.example.integration.web.database.mysql.service;

import com.baomidou.mybatisplus.extension.service.*;
import com.github.jianlu8023.example.integration.web.database.mysql.entity.*;

import java.util.*;


public interface TbUserService extends IService<TbUser> {

    TbUser create();

    List<TbUser> list(Integer pageNo, Integer pageSize);
}
