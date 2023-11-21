package com.example.coursespring.create_course.repository;

import com.example.coursespring.create_course.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course,Integer> {
    @Override
    List<Course> findAll();
}
