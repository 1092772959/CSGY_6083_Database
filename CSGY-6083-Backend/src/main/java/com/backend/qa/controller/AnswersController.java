package com.backend.qa.controller;

import com.backend.qa.common.CustomResponse;
import com.backend.qa.common.CustomResponseStatus;
import com.backend.qa.domain.Answers;
import com.backend.qa.service.AnswersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Api(tags = "Answers")
@RestController
public class AnswersController {

    @Autowired
    private AnswersService answersService;

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

}
