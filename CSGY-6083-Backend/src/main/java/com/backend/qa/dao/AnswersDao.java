package com.backend.qa.dao;

import com.backend.qa.domain.Answers;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

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

    @Select("select distinct a.ans_id, u.uid, u.username, ques_id, date, ans_body, thumb_ups, isBest,\n" +
            "                IFNULL(tmp.userCount, 0) as likedByUser\n" +
            "from Answers a\n" +
            "left join (select ans_id, COUNT(*) as userCount\n" +
            "       from `Like`\n" +
            "where uid = #{uid}\n" +
            "group by ans_id) tmp on tmp.ans_id = a.ans_id\n" +
            "join User u on u.uid = a.uid\n" +
            "where a.ques_id = #{id}\n" +
            "order by thumb_ups desc, date desc;")
    ArrayList<Map<Object, Object>> getAllAnswersByQuesIdAndUid(@Param("id") int ques_id, @Param("uid") int uid);

    @Insert("insert into Answers (uid, ques_id, date, ans_body) values(#{uid},#{ques_id},#{date},#{ans_body})")
    int insert(@Param("uid") int uid, @Param("ques_id") int ques_id, @Param("date") Date date, @Param("ans_body") String ans_body);

    @Update("update Answers set thumb_ups = #{thumb_ups} where ans_id = #{id}")
    int updateThumb(@Param("id") int id, @Param("thumb_ups") int thumb_ups);

    @Update("update Answers set isBest = #{isBest} where ans_id = #{id}")
    int updateBest(@Param("id") int id, @Param("isBest") boolean isBest);

    @Update("update Answers set thumb_ups = thumb_ups + 1 where ans_id = #{id}")
    int incrementThumb(@Param("id") int id);

    @Update("update Answers set thumb_ups = thumb_ups - 1 where ans_id = #{id}")
    int decrementThumb(@Param("id") int id);

    @Select("select count(*) from Answers where ques_id = #{qid} and isBest = 1")
    int getHasBest(@Param("qid") int ques_id);

    @Update("update Answers set isBest = #{isBest} where ans_id = #{aid}")
    int updateIsBest(@Param("aid") int ans_id, @Param("isBest") boolean isBest);
}
