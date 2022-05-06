package com.backend.qa.controller;

import com.backend.qa.common.CustomResponse;
import com.backend.qa.common.CustomResponseStatus;
import com.backend.qa.domain.Topic;
import com.backend.qa.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Api(tags = "Topic")
@RestController
public class TopicController {
    @Autowired
    private TopicService topicService;

    @ApiOperation("Get All Topics")
    @GetMapping(value = "/topics")
    @ResponseBody
    public CustomResponse<ArrayList<Topic>> getTopics() {
        ArrayList<Topic> topics = new ArrayList<Topic>();
        CustomResponse<ArrayList<Topic>> result = CustomResponse.build();
        topics = topicService.getAllTopics();
        if(topics == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(topics);
        return result;
    }

    @ApiOperation("Get Topic By Id")
    @GetMapping(value = "/topics/{id}")
    @ResponseBody
    public CustomResponse<Topic> getTopicById(@PathVariable("id") int id){
        CustomResponse<Topic> result = CustomResponse.build();
        Topic topic = topicService.getById(id);
        if(topic == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(topic);
        return result;
    }

}
