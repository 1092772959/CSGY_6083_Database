package com.backend.qa.dao;

import com.backend.qa.domain.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface LikeDao {
    @Select("select * from `Like` where uid = #{id}")
    public Like getLikeByUid(@Param("id") int id);

    @Select("select * from `Like` where ans_id = #{id}")
    public Like getLikeByAnsId(@Param("id") int id);

    @Select("select * from `Like`")
    public ArrayList<Like> getAllLikes();
}
