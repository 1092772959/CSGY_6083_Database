package com.backend.qa.dao;

import com.backend.qa.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface UserDao {
    @Select("select * from User where uid = #{uid}")
    public User getByUid(@Param("uid") int uid);

    @Select({"select * from User"})
    ArrayList<User> getAllUsers();

    @Select({"select * from User where username = #{username}"})
    User getByUsername(@Param("username") String username);

    @Insert("insert into User (username,password, email) values(#{username},#{password},#{email})")
    int insert(@Param("username") String username, @Param("password") String password, @Param("email") String email);
}