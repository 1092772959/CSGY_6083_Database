package com.backend.qa.dao;

import com.backend.qa.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface UserDao {
    @Select("select uid, username, email, profile, city, state, country, level from User where uid = #{uid}")
    public User getByUid(@Param("uid") int uid);

    @Select({"select * from User"})
    ArrayList<User> getAllUsers();

    @Select({"select * from User where username = #{username}"})
    User getByUsername(@Param("username") String username);

    @Insert("insert into User (username,password, email) values(#{username},#{password},#{email})")
    int insert(@Param("username") String username, @Param("password") String password, @Param("email") String email);

    @Update("update User set username=#{username}, password=#{password}, email=#{email}, profile=#{profile}, state=#{state}, country=#{country}, city=#{city} where uid=#{uid}")
    boolean profile(@Param("uid") String uid,
                    @Param("username")  String username,
                    @Param("password") String password,
                    @Param("email") String email,
                    @Param("profile") String profile,
                    @Param("state") String state,
                    @Param("country") String country,
                    @Param("city") String city);
}