package com.backend.qa.dao;

import com.backend.qa.domain.Questions;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Date;

@Mapper
public interface QuestionsDao {
    @Select("select * from Questions where ques_id = #{id}")
    Questions getQuestionById(@Param("id") int id);

    @Select("select * from Questions order by date desc")
    ArrayList<Questions> getAllQuestions();

    @Select("select * from Questions where uid = #{uid} order by date desc")
    ArrayList<Questions> getAllQuestionsByUid(@Param("uid") int uid);

    @Select("select * from Questions where topic_id = #{topic_id} order by date desc")
    ArrayList<Questions> getAllQuestionsByTopicId(@Param("topic_id") int topic_id);

    @Select("select * from Questions where uid = #{uid} and topic_id = #{topic_id} order by date desc")
    ArrayList<Questions> getAllQuestionsByUidAndTopicId(@Param("uid") int uid, @Param("topic_id") int topic_id);

    @Insert("insert into Questions (uid, topic_id, date, title, ques_body) values(#{uid},#{topic_id},#{date},#{title},#{ques_body})")
    int insert(@Param("uid") int uid, @Param("topic_id") int topic_id, @Param("date") Date date, @Param("title") String title, @Param("ques_body") String ques_body);

    @Update("update Questions set isSolved = #{isSolved} where ques_id = #{id}")
    int updateSolved(@Param("id") int uid, @Param("id") boolean isSolved);
}
