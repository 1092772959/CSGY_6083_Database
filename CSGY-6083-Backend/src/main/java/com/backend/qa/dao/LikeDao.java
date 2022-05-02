package com.backend.qa.dao;

import com.backend.qa.domain.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface LikeDao {
    @Select("select * from `Like` where ans_id = #{id}")
    public ArrayList<Like>  getLikeByAnsId(@Param("id") int id);

    @Select("select * from `Like`")
    public ArrayList<Like> getAllLikes();

    @Select("select * from `Like` inner join User on User.uid = Like.uid and username = #{username}")
    ArrayList<Like> getAllLikesByUsername(@Param("username") String username);
}
