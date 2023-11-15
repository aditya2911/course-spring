package com.example.coursespring.create_course.controllers;


import com.example.coursespring.create_course.models.Course;
import com.example.coursespring.create_course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private CourseRepository courseRepository;


    @Autowired
    CourseController(CourseRepository repo){
        this.courseRepository = repo;
    }

    @PostMapping("/save-course")
    ResponseEntity<String> saveCourse(@RequestBody Course course){
        try{
            courseRepository.save(course);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.toString());

        }
        return ResponseEntity.ok("Course Saved successfully");
    }
 }
