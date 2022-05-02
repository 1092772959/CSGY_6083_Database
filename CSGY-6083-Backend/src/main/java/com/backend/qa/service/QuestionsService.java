package com.backend.qa.service;

import com.backend.qa.dao.QuestionsDao;
import com.backend.qa.domain.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QuestionsService {
    @Autowired
    private QuestionsDao questionsDao;

    public Questions getQuestionById(int id){
        return questionsDao.getQuestionById(id);
    }

    public ArrayList<Questions> getAllQuestions() {
        return questionsDao.getAllQuestions();
    }

    public ArrayList<Questions> getAllQuestionsByUsername(String username) {
        return questionsDao.getAllQuestionsByUsername(username);
    }

}
