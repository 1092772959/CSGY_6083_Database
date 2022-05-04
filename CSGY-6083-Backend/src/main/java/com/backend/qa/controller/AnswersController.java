package com.backend.qa.controller;

import com.backend.qa.common.CustomResponse;
import com.backend.qa.common.CustomResponseStatus;
import com.backend.qa.config.AccessLimit;
import com.backend.qa.domain.Answers;
import com.backend.qa.service.AnswersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(tags = "Answers")
@RestController
public class AnswersController {

    @Autowired
    private AnswersService answersService;

    @ApiOperation("Get All Answers Posted By User")
    @AccessLimit(needLogin = true)
    @GetMapping(value = "/answers/user")
    @ResponseBody
    public CustomResponse<ArrayList<Answers>> getAnswersByUsername(String username){
        ArrayList<Answers> answers = new ArrayList<Answers>();
        CustomResponse<ArrayList<Answers>> result = CustomResponse.build();
        answers = answersService.getAnswersByUsername(username);
        if(answers == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(answers);
        return result;
    }

    @ApiOperation("Get All Answers")
    @GetMapping(value = "/answers")
    @ResponseBody
    public CustomResponse<ArrayList<Answers>> getAnswerById(){
        ArrayList<Answers> answers = new ArrayList<Answers>();
        CustomResponse<ArrayList<Answers>> result = CustomResponse.build();
        answers = answersService.getAllAnswers();
        if(answers == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(answers);
        return result;
    }

    @ApiOperation("Get Answer By Id")
    @GetMapping(value = "/answers/{id}")
    @ResponseBody
    public CustomResponse<Answers> getAnswerById(@PathVariable("id") int id){
        CustomResponse<Answers> result = CustomResponse.build();
        Answers answers = answersService.getAnswerById(id);
        if(answers == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(answers);
        return result;
    }

    @ApiOperation("Get Answer By Question and User")
    @GetMapping(value = "/answers/question")
    @ResponseBody
    public CustomResponse<List<Map<Object, Object>>> getAnswerById(Integer ques_id, Integer uid){
        CustomResponse<List<Map<Object, Object>>> result = CustomResponse.build();
        List<Map<Object, Object>> answers = answersService.getAnswersByQuestionAndUser(ques_id, uid);
        if(answers == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(answers);
        return result;
    }

    @ApiOperation("Post a New Answer with Body")
    @PostMapping(value = "/answers")
    @ResponseBody
    public CustomResponse<Boolean> postAnAnswer(Integer ques_id, Integer uid, @RequestParam("body") String body){
        CustomResponse<Boolean> result = CustomResponse.build();
        if(!answersService.postAnAnswer(ques_id, uid, body)){
            result.withError(CustomResponseStatus.FAILD.getCode(), CustomResponseStatus.FAILD.getMessage());
            return result;
        }
        result.setData(true);
        return result;
    }
}
