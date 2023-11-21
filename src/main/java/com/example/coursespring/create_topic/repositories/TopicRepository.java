package com.example.coursespring.create_topic.repositories;

import com.example.coursespring.create_topic.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TopicRepository extends MongoRepository<Topic, Integer> {

    Topic findByEmail(String email);
}
