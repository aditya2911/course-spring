package com.example.coursespring.create_topic.repositories;

import com.example.coursespring.create_topic.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TopicRepository extends MongoRepository<Topic, String> {

    List<Topic> findByCourseId(String courseId);
}
