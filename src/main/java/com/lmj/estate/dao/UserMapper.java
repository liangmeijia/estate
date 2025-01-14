package com.lmj.estate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmj.estate.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Update("update user set age = age - #{age} where id = #{id}")
    void deductionAge(@Param("id") long id ,@Param("age") int age);
    @Update("update user set balance = balance + #{balance} where id = #{id}")
    void increaseBalance(@Param("id") long id ,@Param("balance") Double balance);
    int batchInsert(@Param("users") List<User> users);
    @Select("select * from user where name = #{name} ")
    User selectOneByName(@Param("name") String name);

}
