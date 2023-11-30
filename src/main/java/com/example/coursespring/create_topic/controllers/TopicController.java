package com.example.coursespring.create_topic.controllers;

import com.example.coursespring.auth.config.appConstants;

import com.example.coursespring.create_topic.dto.topicDTO;

import com.example.coursespring.create_topic.service.topicService;
import com.example.coursespring.response.ApiResponse;

import com.example.coursespring.response.TopicResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController()
@RequestMapping("/api/v1/topic")
public class TopicController {

    @Autowired
    private topicService tService;

    //save topics
    @PostMapping("/save-topic")
    public ResponseEntity<topicDTO> createTopic(
            @RequestBody topicDTO topicDto) {
        topicDTO newTopic = this.tService.createTopic(topicDto);
        return new ResponseEntity<>(newTopic, HttpStatus.CREATED);
    }

    //update topics
    @PutMapping(value = "/{topicId}")
    public ResponseEntity<topicDTO> updateTopics(@Valid @RequestBody topicDTO topicDto, @PathVariable("topicId") String tid){
        topicDTO updateTopic = this.tService.updateTopic(topicDto,tid);
        return ResponseEntity.ok(updateTopic);
    }
    //delete topics
    @DeleteMapping("/{topicId}")
    public ResponseEntity<ApiResponse> deleteTopics(@PathVariable("topicId") String tid){
        this.tService.deleteTopic(tid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Topic " + tid + " Deleted Successfully",true),HttpStatus.OK);
    }


    @GetMapping("/allTopics/{courseId}")
    public ResponseEntity<List<TopicResponse>> getAllTopicsByCourseId(@PathVariable String courseId){

        ArrayList<TopicResponse> listOfTopics = new ArrayList<>();
        return ResponseEntity.ok(listOfTopics);
    }
    //get all topics
    @GetMapping("/alltopics")
    public ResponseEntity<TopicResponse> getAllTopics(
            @RequestParam(value="pageNumber",defaultValue = appConstants.PAGE_NUMBER, required = false) Integer pagenum,
            @RequestParam(value="pageSize",defaultValue = appConstants.PAGE_SIZE, required = false) Integer pagesize,
            @RequestParam(value="sortBy",defaultValue = appConstants.SORT_BY,required = false)String sortBy,
            @RequestParam(value="sortOrder",defaultValue = appConstants.SORT_ORDER,required = false)String sortOrder
    ){
        TopicResponse topicResponse = this.tService.getAllTopics(pagenum,pagesize,sortBy,sortOrder);
        return new ResponseEntity<>(topicResponse,HttpStatus.OK);
    }


}


//,
//        "dateAdded": "29-08-2001"