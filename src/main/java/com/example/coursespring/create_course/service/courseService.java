package com.example.coursespring.create_course.service;

import com.example.coursespring.create_course.dto.courseDTO;
import com.example.coursespring.response.PostResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface courseService {
    //create course
    courseDTO createCourse(courseDTO coursedto);

    //update course
    courseDTO updateCourse(courseDTO courseDTO, String cid);


    List<courseDTO> getCoursesByEmail(String email);
    //delete courses
    void deleteCourse(String id);

    //get all course
    PostResponse getAllCourses(Integer pagenumber, Integer pagesize, String sortBy, String sortOrder);

    //get course by id
    courseDTO getCourseById(String id);

    //get all course by user
    List<courseDTO> getAllCoursesByUser(String uid);

    //search course
    List<courseDTO> searchCourse(String search);
}
