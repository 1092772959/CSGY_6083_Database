package com.backend.qa.service;

import com.backend.qa.dao.TopicDao;
import com.backend.qa.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TopicService {
    @Autowired
    public TopicDao topicDao;

    public Topic getById(int id){
        return topicDao.getById(id);
    }

    public ArrayList<Topic> getAllTopics() {
        return topicDao.getAllTopics();
    }
}
