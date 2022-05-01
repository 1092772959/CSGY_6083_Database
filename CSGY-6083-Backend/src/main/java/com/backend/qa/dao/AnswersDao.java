package com.backend.qa.dao;

import com.backend.qa.domain.Answers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface AnswersDao {
    @Select("select * from Answers where ans_id = #{id}")
    public Answers getAnswerById(@Param("id") int id);

    @Select("select * from Answers")
    ArrayList<Answers> getAllAnswers();
}
