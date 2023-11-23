package com.example.coursespring.create_topic.service;

import com.example.coursespring.create_topic.dto.topicDTO;
import com.example.coursespring.response.TopicResponse;

public interface topicService {
    topicDTO createTopic(topicDTO topicDto);

    topicDTO updateTopic(topicDTO topicDto, String tid);

    void deleteTopic(String tid);

    TopicResponse getAllTopics(Integer pagenumber, Integer pagesize, String sortBy, String sortOrder);

}
