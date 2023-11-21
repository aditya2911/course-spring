package com.example.coursespring.create_topic.service;

import com.example.coursespring.create_course.dto.courseDTO;
import com.example.coursespring.response.PostResponse;

import java.util.List;

public interface courseService {
    //update course
    courseDTO updateCourse(courseDTO courseDTO, Integer cid);

    //delete courses
    void deleteCourse(Integer id);

    //get all course
    PostResponse getAllCourses(Integer pagenumber, Integer pagesize, String sortBy, String sortOrder);

    //get course by id
    courseDTO getCourseById(Integer id);

    //get all course by topic
    List<courseDTO> getAllCoursesByTopic(Integer tid);

    //get all course by user
    List<courseDTO> getAllCoursesByUser(Integer uid);

    //search course
    List<courseDTO> searchCourse(String search);
}
