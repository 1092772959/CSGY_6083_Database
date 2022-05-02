package com.backend.qa.dao;

import com.backend.qa.domain.Answers;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Date;

@Mapper
public interface AnswersDao {
    @Select("select * from Answers where ans_id = #{id}")
    public Answers getAnswerById(@Param("id") int id);

    @Select("select * from Answers order by date desc")
    ArrayList<Answers> getAllAnswers();

    @Select("select * from Answers inner join User on User.uid = Answers.uid and username = #{username} order by thumb_ups desc, date desc")
    ArrayList<Answers> getAnswersByUsername(@Param("username") String username);

    @Select("select * from Answers where ques_id = #{id} order by thumb_ups desc, date desc")
    ArrayList<Answers> getAllAnswersByQuesId(@Param("id") int id);

    @Insert("insert into Answers (uid, ques_id, date, ans_body) values(#{uid},#{ques_id},#{date},#{ans_body})")
    int insert(@Param("uid") int uid, @Param("ques_id") int ques_id, @Param("date") Date date, @Param("ans_body") String ans_body);

    @Update("update Answers set thumb_ups = #{thumb_ups} where ans_id = #{id}")
    int updateThumb(@Param("id") int id, @Param("thumb_ups") int thumb_ups);

    @Update("update Answers set isBest = #{isBest} where ans_id = #{id}")
    int updateBest(@Param("id") int id, @Param("isBest") boolean isBest);

}
