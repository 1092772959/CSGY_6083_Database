package com.backend.qa.dao;

import com.backend.qa.domain.Questions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface QuestionsDao {
    @Select("select * from Questions where ques_id = #{id}")
    public Questions getQuestionById(@Param("id") int id);

    @Select("select * from Questions")
    ArrayList<Questions> getAllQuestions();
}
