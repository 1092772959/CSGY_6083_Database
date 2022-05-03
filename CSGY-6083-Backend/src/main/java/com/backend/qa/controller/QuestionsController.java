package com.backend.qa.controller;


import com.backend.qa.common.CustomResponse;
import com.backend.qa.common.CustomResponseStatus;
import com.backend.qa.config.AccessLimit;
import com.backend.qa.domain.Questions;
import com.backend.qa.domain.User;
import com.backend.qa.service.QuestionsService;
import com.backend.qa.service.SessionManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(tags = "Questions")
@RestController
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private SessionManager sessionManager;

    @ApiOperation("Get All Questions Posted By User")
    @AccessLimit(needLogin = true)
    @GetMapping(value = "/questions/user")
    @ResponseBody
    public CustomResponse<List<Map<Object, Object>>> getQuestionsByUsername(Integer uid){
        CustomResponse<List<Map<Object, Object>>> result = CustomResponse.build();
        List<Map<Object, Object>> questions = new ArrayList<Map<Object, Object>>();
        questions = questionsService.getAllQuestionsByUId(uid);
        if(questions == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(questions);
        return result;
    }

    @ApiOperation("Get All Unsolved Questions")
    @GetMapping(value = "/questions")
    @ResponseBody
    public CustomResponse<ArrayList<Questions>> getAllQuestions(){
        CustomResponse<ArrayList<Questions>> result = CustomResponse.build();
        ArrayList<Questions> questions = new ArrayList<Questions>();
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

    @ApiOperation("Set Question to be solved or not by id")
    @PostMapping(value = "/questions/solved")
    @ResponseBody
    public CustomResponse<Boolean> setQuestionSolvedById(Integer id, Boolean solved){
        CustomResponse<Boolean> result = CustomResponse.build();
        questionsService.setQuestionSolvedById(id, solved);
        result.setData(true);
        return result;
    }

    @ApiOperation("Post a new Question with title and body")
    @PostMapping(value = "/questions/user")
    @ResponseBody
    public CustomResponse<Boolean> postAQuestion(Integer uid, @RequestParam("topic_id") Integer topic_id, @RequestParam("title") String title,
                                                 @RequestParam("body") String body){
        CustomResponse<Boolean> result = CustomResponse.build();
        if(!questionsService.postAQuestion(uid, topic_id, title, body)){
            result.withError(CustomResponseStatus.ERROR.getCode(), CustomResponseStatus.ERROR.getMessage());
            return result;
        }
        result.setData(true);
        return result;
    }
}
