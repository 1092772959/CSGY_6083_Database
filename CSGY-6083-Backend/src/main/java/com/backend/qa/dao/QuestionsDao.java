package com.backend.qa.dao;

import com.backend.qa.domain.Questions;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Mapper
public interface QuestionsDao {
    @Select("select * from Questions where ques_id = #{id}")
    Questions getQuestionById(@Param("id") int id);

    @Select("select * from Questions where isSolved = 0 order by date desc")
    ArrayList<Questions> getAllQuestions();

    @Select("select * from Questions where topic_id = #{topic_id} order by date desc")
    ArrayList<Questions> getAllQuestionsByTopicId(@Param("topic_id") int topic_id);

    @Select("select * from Questions where uid = #{uid} and topic_id = #{topic_id} order by date desc")
    ArrayList<Questions> getAllQuestionsByUidAndTopicId(@Param("uid") int uid, @Param("topic_id") int topic_id);

    @Insert("insert into Questions (uid, topic_id, date, title, ques_body) values(#{uid},#{topic_id},#{date},#{title},#{ques_body})")
    int insert(@Param("uid") int uid, @Param("topic_id") int topic_id, @Param("date") Date date, @Param("title") String title, @Param("ques_body") String ques_body);

    @Update("update Questions set isSolved = #{isSolved} where ques_id = #{id}")
    int updateSolved(@Param("id") int uid, @Param("isSolved") boolean isSolved);

    @Select("select * from Questions inner join User on User.uid = Questions.uid and username = #{username} order by date desc")
    ArrayList<Questions> getAllQuestionsByUsername(@Param("username") String username);

    @Select("select distinct q.ques_id, u.uid, u.username, q.topic_id, q.date, q.title, q.ques_body, q.isSolved, \n " +
            "(case when tmp.hasBest is null THEN 0 ELSE tmp.hasBest END) as hasBest, \n" +
            "(case when t.parent_id is null THEN -1 ELSE t.parent_id END) as p_topic_id \n" +
            "from Questions q join User u on u.uid = q.uid join topic t on t.topic_id = q.topic_id \n" +
            "left join (select a.ques_id, 1 as hasBest from Answers a where a.ques_id = #{id} and a.isBest = 1) tmp on tmp.ques_id = q.ques_id \n" +
            "where q.ques_id = #{id} \n" +
            "order by date desc")
    Map<Object, Object> getQuestionKVById(@Param("id") Integer id);

    @Select("select distinct q.ques_id, u.uid, u.username, q.topic_id, q.date, q.title, q.ques_body, q.isSolved, \n" +
            "(case when tmp.hasBest is null THEN 0 ELSE tmp.hasBest END) as hasBest, \n" +
            "(case when t.parent_id is null THEN -1 ELSE t.parent_id END) as p_topic_id \n" +
            "from Questions q inner join User u on u.uid = q.uid and u.uid = #{uid} join topic t on t.topic_id = q.topic_id \n" +
            "left join (select a.ques_id, 1 as hasBest from Answers a where a.isBest = 1) tmp on tmp.ques_id = q.ques_id \n" +
            "order by date desc")
    ArrayList<Map<Object, Object>> getAllQuestionsByUserId(@Param("uid") Integer uid);

    @Select("select distinct q.ques_id, u.uid, u.username, q.topic_id, q.date, q.title, q.ques_body, q.isSolved, \n " +
            "(case when tmp.hasBest is null THEN 0 ELSE tmp.hasBest END) as hasBest, \n" +
            "(case when t.parent_id is null THEN -1 ELSE t.parent_id END) as p_topic_id \n" +
            "from Questions q join User u on u.uid = q.uid join topic t on t.topic_id = q.topic_id \n" +
            "left join (select ques_id, 1 as hasBest from Answers where isBest = 1) tmp on tmp.ques_id = q.ques_id \n" +
            "order by date desc")
    ArrayList<Map<Object, Object>> getAllQuestionsKV();
}
