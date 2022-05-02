package com.backend.qa.service;

import com.backend.qa.dao.AnswersDao;
import com.backend.qa.domain.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AnswersService {
    @Autowired
    private AnswersDao answersDao;

    public Answers getAnswerById(int id){
        return answersDao.getAnswerById(id);
    }

    public ArrayList<Answers> getAllAnswers() {
        return answersDao.getAllAnswers();
    }

    public ArrayList<Answers> getAnswersByUsername(String username) {
        return answersDao.getAnswersByUsername(username);
    }
}
