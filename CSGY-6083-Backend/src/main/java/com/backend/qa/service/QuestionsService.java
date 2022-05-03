package com.backend.qa.service;

import com.backend.qa.dao.QuestionsDao;
import com.backend.qa.dao.TopicDao;
import com.backend.qa.domain.Questions;
import com.backend.qa.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuestionsService {
    @Autowired
    private QuestionsDao questionsDao;

    @Autowired
    private TopicDao topicDao;

    public Questions getQuestionById(int id){
        return questionsDao.getQuestionById(id);
    }

    public ArrayList<Questions> getAllQuestions() {
        return questionsDao.getAllQuestions();
    }

    public ArrayList<Questions> getAllQuestionsByUsername(String username) {
        return questionsDao.getAllQuestionsByUsername(username);
    }

    public ArrayList<Map<Object, Object>> getAllQuestionsByUId(Integer uid) {
        ArrayList<Map<Object, Object>> res = questionsDao.getAllQuestionsByUserId(uid);
        for (Map item : res) {
            List<String> topicNames = new ArrayList<>();
            Integer topicId = (Integer)item.get("topic_id");
            Topic topic = topicDao.getById(topicId);
            topicNames.add(topic.getTopic_name());

            Long parentId = (Long)item.get("p_topic_id");
            Topic parentTopic = topicDao.getById(parentId.intValue());
            topicNames.add(parentTopic.getTopic_name());

            item.put("tags", topicNames);
        }
        return res;
    }

    public void setQuestionSolvedById(Integer id, Boolean solved) {
        questionsDao.updateSolved(id, solved);
    }
}
