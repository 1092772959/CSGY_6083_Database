package com.backend.qa.dao;

import com.backend.qa.domain.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface TopicDao {
    @Select("select * from Topic where topic_id = #{id}")
    Topic getById(@Param("id") int id);

    @Select({"select * from Topic"})
    ArrayList<Topic> getAllTopics();
}
