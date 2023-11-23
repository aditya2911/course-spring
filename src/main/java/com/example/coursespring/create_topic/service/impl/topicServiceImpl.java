package com.example.coursespring.create_topic.service.impl;

import com.example.coursespring.create_course.dto.courseDTO;
import com.example.coursespring.create_course.models.Course;
import com.example.coursespring.create_topic.dto.topicDTO;
import com.example.coursespring.create_topic.model.Topic;
import com.example.coursespring.create_topic.repositories.TopicRepository;
import com.example.coursespring.create_topic.service.topicService;
import com.example.coursespring.exceptions.ResourceNotFoundException;
import com.example.coursespring.response.PostResponse;
import com.example.coursespring.response.TopicResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class topicServiceImpl implements topicService {

    @Autowired
    private TopicRepository trepo;

    @Autowired
    private ModelMapper model;

    @Override
    public topicDTO createTopic(topicDTO topicDto) {
        Topic topic = this.model.map(topicDto, Topic.class);
        topic.setTitle(topicDto.getTitle());
        topic.setBlog(topicDto.getBlog());
        topic.setEmail(topicDto.getEmail());
        topic.setDateAdded(new Date());
        topic.setCourseId(topicDto.getCourseId());

        Topic newTopic = this.trepo.save(topic);

        return this.model.map(newTopic,topicDTO.class);
    }

    @Override
    public topicDTO updateTopic(topicDTO topicDto,String tid) {
        Topic topic = this.trepo.findById(tid)
                .orElseThrow(() -> new ResourceNotFoundException("Topic","ID",tid));
        topic.setTitle(topicDto.getTitle());
        topic.setBlog(topicDto.getBlog());
        topic.setDateAdded(new Date());

        Topic updateTopic = this.trepo.save(topic);
        return this.model.map(updateTopic, topicDTO.class);
    }

    @Override
    public void deleteTopic(String tid) {
        Topic topic = this.trepo.findById(tid)
                .orElseThrow(() -> new ResourceNotFoundException("Topic","ID",tid));
        this.trepo.delete(topic);

    }

    @Override
    public TopicResponse getAllTopics(Integer pagenumber, Integer pagesize, String sortBy, String sortOrder) {
        Sort sort = (sortOrder.equalsIgnoreCase("asc"))?sort = Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pagenumber,pagesize,sort);
        Page<Topic> pageTopic = this.trepo.findAll(p);

        List<Topic> posts = pageTopic.getContent();
        List<topicDTO> topicDto = posts.stream().map((post) -> this.model.map(post,topicDTO.class))
                .collect(Collectors.toList());

        TopicResponse topicresponse = new TopicResponse();
        topicresponse.setContent(topicDto);
        topicresponse.setPageNumber(pageTopic.getNumber());
        topicresponse.setPageSize(pageTopic.getSize());
        topicresponse.setTotalElements(pageTopic.getTotalElements());
        topicresponse.setTotalPages(pageTopic.getTotalPages());
        topicresponse.setLastPage(pageTopic.isLast());


        return topicresponse;
    }
}
