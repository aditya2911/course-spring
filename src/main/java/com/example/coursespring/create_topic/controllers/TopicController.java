package com.example.coursespring.create_topic.controllers;

import com.example.coursespring.create_topic.model.Topic;
import com.example.coursespring.create_topic.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/api/v1/topic")
public class TopicController {

    private final TopicRepository topicRepository;

    @Autowired
    TopicController(TopicRepository tp){
        this.topicRepository = tp;
    }

    @PostMapping("/save-topic")
    ResponseEntity<String> saveTopic(@RequestBody Topic topic){
        System.out.println("endpoint topic hittinh");
        try{
            System.out.print("Incomming topic is"+topic);
            topicRepository.save(topic);
        }
        catch (Exception e){
            System.out.println("Topic Exception "+e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.toString());
        }
        return ResponseEntity.ok("Topic saved   Successfully");
    }
}
