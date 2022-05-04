package com.backend.qa.dao;

import com.backend.qa.domain.Like;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface LikeDao {
    @Select("select * from `Like` where ans_id = #{id}")
    public ArrayList<Like>  getLikeByAnsId(@Param("id") int id);

    @Select("select * from `Like`")
    public ArrayList<Like> getAllLikes();

    @Select("select * from `Like` inner join User on User.uid = Like.uid and username = #{username}")
    ArrayList<Like> getAllLikesByUsername(@Param("username") String username);

    @Insert("insert into `Like` (uid, ans_id) values(#{uid},#{ans_id})")
    int insert(@Param("uid") Integer uid, @Param("ans_id") Integer ans_id);

    @Delete("DELETE FROM `Like` where uid = #{uid} and ans_id = #{ans_id}")
    boolean delete(@Param("uid") Integer uid, @Param("ans_id") Integer ans_id);
}
