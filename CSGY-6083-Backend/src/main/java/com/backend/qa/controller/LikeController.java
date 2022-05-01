package com.backend.qa.controller;

import com.backend.qa.common.CustomResponse;
import com.backend.qa.common.CustomResponseStatus;
import com.backend.qa.domain.Like;
import com.backend.qa.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Api(tags = "Like")
@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

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

    @ApiOperation("Get Like By Uid")
    @GetMapping(value = "/likes/uid/{id}")
    @ResponseBody
    public CustomResponse<Like> getLikeByUid(@PathVariable("id") int id){
        CustomResponse<Like> result = CustomResponse.build();
        Like like = likeService.getLikeByUid(id);
        if(like == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(like);
        return result;
    }

    @ApiOperation("Get Like By Ans Id")
    @GetMapping(value = "/likes/ans/{id}")
    @ResponseBody
    public CustomResponse<Like> getLikeByAnsId(@PathVariable("id") int id){
        CustomResponse<Like> result = CustomResponse.build();
        Like like = likeService.getLikeByAnsId(id);
        if(like == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(like);
        return result;
    }
}
