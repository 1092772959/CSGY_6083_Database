package com.backend.qa.controller;


import com.backend.qa.common.CustomResponse;
import com.backend.qa.common.CustomResponseStatus;
import com.backend.qa.domain.Questions;
import com.backend.qa.service.QuestionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Api(tags = "Questions")
@RestController
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @ApiOperation("Get All Questions")
    @GetMapping(value = "/questions")
    @ResponseBody
    public CustomResponse<ArrayList<Questions>> getUserByUid(){
        ArrayList<Questions> questions = new ArrayList<Questions>();
        CustomResponse<ArrayList<Questions>> result = CustomResponse.build();
        questions = questionsService.getAllQuestions();
        if(questions == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(questions);
        return result;
    }

    @ApiOperation("Get Question By Id")
    @GetMapping(value = "/questions/{id}")
    @ResponseBody
    public CustomResponse<Questions> getQuestionsById(@PathVariable("id") int id){
        CustomResponse<Questions> result = CustomResponse.build();
        Questions question = questionsService.getQuestionById(id);
        if(question == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(question);
        return result;
    }

}
