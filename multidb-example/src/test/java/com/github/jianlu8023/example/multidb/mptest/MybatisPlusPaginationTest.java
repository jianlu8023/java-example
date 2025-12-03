package com.github.jianlu8023.example.multidb.mptest;

import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.jianlu8023.multidbexample.web.db1.entity.*;
import com.github.jianlu8023.multidbexample.web.db1.service.*;
import com.github.jianlu8023.utils.generator.pojo.*;
import com.github.jianlu8023.utils.generator.utils.*;
import com.github.pagehelper.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.*;

@SpringBootTest
public class MybatisPlusPaginationTest {

    @Autowired
    private PojoGenerator<User> pojoGenerator;

    @Autowired
    private UserService userService;


    void init(Integer size) {
        long count = userService.count();
        if (0 == count) {
            insert(size);
        }
    }

    void insert(Integer size) {
        List<User> insert = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            User mock = pojoGenerator.generate(User.class);
            mock.setCreateTime(new Date());
            mock.setUpdateTime(new Date());
            insert.add(mock);
        }
        // insert.forEach(entity -> System.out.println(JsonUtils.toJSONString(entity)));
        // userService.saveBatch(insert);
        userService.insertBatch(insert);
    }

    @Test
    void paginationTest() {
        int pageSize = 10;
        init(1000);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(User::getUid);
        long count = userService.count(wrapper);

        long pageSum = count / pageSize == 0 ? (count / pageSize) : ((count / pageSize) + 1);

        for (int i = 1; i <= pageSum; i++) {
            PageHelper.startPage(i, pageSize, false);
            List<User> list = new PageInfo<>(userService.list(wrapper)).getList();
            list.forEach(entity -> System.out.println(JsonUtils.toJSONString(entity)));
        }
        System.out.println("-------------------------------------------------------");

        for (int i = 1; i <= pageSum; i++) {
            com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> page1 = userService.page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(i, pageSize, false), wrapper);
            List<User> records = page1.getRecords();
            records.forEach(entity -> System.out.println(JsonUtils.toJSONString(entity)));
        }
    }

}
