package com.backend.qa.controller;

import com.backend.qa.common.CustomResponse;
import com.backend.qa.common.CustomResponseStatus;
import com.backend.qa.config.AccessLimit;
import com.backend.qa.domain.Answers;
import com.backend.qa.domain.Like;
import com.backend.qa.service.AnswersService;
import com.backend.qa.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Api(tags = "Like")
@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private AnswersService answersService;

    @ApiOperation("Get All Likes")
    @GetMapping(value = "/likes")
    @ResponseBody
    public CustomResponse<ArrayList<Like>> getUserByUid(){
        ArrayList<Like> likes = new ArrayList<Like>();
        CustomResponse<ArrayList<Like>> result = CustomResponse.build();
        likes = likeService.getAllLikes();
        if(likes == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(likes);
        return result;
    }

    @ApiOperation("Get All Likes Liked By User")
    @AccessLimit(needLogin = true)
    @GetMapping(value = "/likes/user")
    @ResponseBody
    public CustomResponse<ArrayList<Like>> getLikeByUsername(String username){
        ArrayList<Like> likes = new ArrayList<Like>();
        CustomResponse<ArrayList<Like>> result = CustomResponse.build();
        likes = likeService.getLikeByUsername(username);
        if(likes == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(likes);
        return result;
    }

    @ApiOperation("Get All Likes By Ans Id")
    @GetMapping(value = "/likes/ans/{id}")
    @ResponseBody
    public CustomResponse<ArrayList<Like>> getLikeByAnsId(@PathVariable("id") int id){
        ArrayList<Like> likes = new ArrayList<Like>();
        CustomResponse<ArrayList<Like>> result = CustomResponse.build();
        likes = likeService.getLikeByAnsId(id);
        if(likes == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(likes);
        return result;
    }

    @ApiOperation("Add like record for one answer and one user")
    @PostMapping(value = "/likes/ans")
    @ResponseBody
    public CustomResponse<Answers> addLikeByAnswerAndUser(Integer uid, Integer ans_id) {
        CustomResponse<Answers> result = CustomResponse.build();
        likeService.addLike(uid, ans_id);
        Answers answer = answersService.getAnswerById(ans_id);
        result.setData(answer);
        return result;
    }

    @ApiOperation("Add like record for one answer and one user")
    @DeleteMapping(value = "/likes/ans")
    @ResponseBody
    public CustomResponse<Answers> deleteLikeByAnswerAndUser(Integer uid, Integer ans_id) {
        CustomResponse<Answers> result = CustomResponse.build();
        likeService.deleteLike(uid, ans_id);
        Answers answer = answersService.getAnswerById(ans_id);
        result.setData(answer);
        return result;
    }
}
