package com.example.coursespring.create_topic.service.impl;

import com.example.coursespring.create_course.dto.courseDTO;
import com.example.coursespring.create_course.models.Course;
import com.example.coursespring.create_course.repository.CourseRepository;

import com.example.coursespring.create_topic.model.Topic;
import com.example.coursespring.create_topic.repositories.TopicRepository;
import com.example.coursespring.create_topic.service.courseService;
import com.example.coursespring.exceptions.ResourceNotFoundException;
import com.example.coursespring.response.PostResponse;
import com.example.coursespring.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class courseServiceImpl implements courseService {

    @Autowired
    private UserRepository uRepo;
    @Autowired
    private CourseRepository cRepo;

    @Autowired
    private TopicRepository tRepo;

    @Autowired
    private ModelMapper modelmapper;

    @Override
    public courseDTO updateCourse(courseDTO courseDTO, Integer cid) {
        Course course = this.cRepo.findById(cid)
                .orElseThrow(() -> new ResourceNotFoundException("Course","Id",cid));
        course.setCourseTitle(courseDTO.getCourseTitle());
        course.setDescription(courseDTO.getDescription());

        Course updateCourse = this.cRepo.save(course);
        return this.modelmapper.map(updateCourse, courseDTO.class);
    }

    @Override
    public void deleteCourse(Integer cid) {
        Course course = this.cRepo.findById(cid)
                .orElseThrow(() -> new ResourceNotFoundException("Course","Id",cid));
        this.cRepo.delete(course);
    }

    @Override
    public PostResponse getAllCourses(Integer pagenumber, Integer size, String sortBy, String sortOrder) {
        Sort sort = (sortOrder.equalsIgnoreCase("asc"))?sort = Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//        if(sortOrder.equalsIgnoreCase("asc")){
//            sort = Sort.by(sortBy).ascending();
//        }
//        else{
//            sort = Sort.by(sortBy).descending();
//        }
        Pageable p = PageRequest.of(pagenumber,size,sort);
        Page<Course> pageCourse = this.cRepo.findAll(p);

        List<Course> posts = pageCourse.getContent();
        List<courseDTO> courseDTO = posts.stream().map((post) -> this.modelmapper.map(post,courseDTO.class))
                .collect(Collectors.toList());

        PostResponse postresponse = new PostResponse();
        postresponse.setContent(courseDTO);
        postresponse.setPageNumber(pageCourse.getNumber());
        postresponse.setPageSize(pageCourse.getSize());
        postresponse.setTotalElements(pageCourse.getTotalElements());
        postresponse.setTotalPages(pageCourse.getTotalPages());
        postresponse.setLastPage(pageCourse.isLast());


        return postresponse;
    }

    @Override
    public courseDTO getCourseById(Integer cid) {
        Course course = this.cRepo.findById(cid)
                .orElseThrow(() -> new ResourceNotFoundException("Post","Id",cid));
        return this.modelmapper.map(course, courseDTO.class);
    }

    @Override
    public List<courseDTO> getAllCoursesByTopic(Integer tid) {
        Topic topic = this.tRepo.findById(tid)
                .orElseThrow(() -> new ResourceNotFoundException("Category","Id",tid));
        List<Course> posts = this.cRepo.findByTopic()
        List<postDto> postsDto = posts.stream().map((post) -> this.modelmapper.map(post, postDto.class))
                .collect(Collectors.toList());
        return postsDto;
    }

    @Override
    public List<courseDTO> getAllCoursesByUser(Integer uid) {
        return null;
    }

    @Override
    public List<courseDTO> searchCourse(String search) {
        return null;
    }
}
