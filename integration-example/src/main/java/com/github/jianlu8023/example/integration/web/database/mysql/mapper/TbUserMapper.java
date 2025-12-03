package com.github.jianlu8023.example.integration.web.database.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.*;
import com.github.jianlu8023.example.integration.web.database.mysql.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface TbUserMapper extends BaseMapper<TbUser> {

    @Select("select * from tb_user where uid = #{uid}")
    TbUser selectTbUserByUId(@Param("uid") String uid);

    @Select("${sql}")
    List<TbUser> selectTbUserList(@Param("sql") String sql);

    @Insert("<script>" +
            "INSERT INTO ${tableName}" +
            "(uid,username,paswword,full_name,email,phone_number,gender,birth_date,address)" +
            "VALUES" +
            "<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">" +
            "(#{item.uid},\n" +
            "             #{item.username},\n" +
            "             #{item.password},\n" +
            "             #{item.full_name},\n" +
            "             #{item.email},\n" +
            "             #{item.phone_number},\n" +
            "             #{item.gender},\n" +
            "             #{item.birth_date},\n" +
            "             #{item.address}) " +
            "</foreach >" +
            "</script>")
    int insertBatches(@Param("tableName") String tableName, @Param("list") List<TbUser> list);
}




