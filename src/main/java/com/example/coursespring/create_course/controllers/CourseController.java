package com.example.coursespring.create_course.controllers;


import com.example.coursespring.auth.config.appConstants;
import com.example.coursespring.create_course.dto.courseDTO;
import com.example.coursespring.create_course.models.Course;
import com.example.coursespring.create_course.repository.CourseRepository;
import com.example.coursespring.create_course.service.courseService;
import com.example.coursespring.response.ApiResponse;
import com.example.coursespring.response.PostResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    private     courseService courseService;


//    @Autowired
//    CourseController(CourseRepository repo){
//        this.courseRepository = repo;
//    }

//    @PostMapping("/save-course")
//    ResponseEntity<String> saveCourse(@RequestBody Course course){
//        try{
//            courseRepository.save(course);
//        }
//        catch (Exception e){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.toString());
//
//        }
//        return ResponseEntity.ok("Course Saved successfully");
//    }


    //create course

    @PostMapping("/save-course")
    public ResponseEntity<courseDTO> createCourse(
            @RequestBody courseDTO courseDto) {
        courseDTO newCourse = this.courseService.createCourse(courseDto);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }


    @PutMapping(value = "/{courseId}")
    public ResponseEntity<courseDTO> updateCourse(@Valid @RequestBody courseDTO courseDto, @PathVariable("courseId") String cid){
        courseDTO updateCourse = this.courseService.updateCourse(courseDto,cid);
        return ResponseEntity.ok(updateCourse);
    }
     //delete courses
    @DeleteMapping("/{courseId}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable("courseId") String cid){
        this.courseService.deleteCourse(cid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Course " + cid + " Deleted Successfully",true),HttpStatus.OK);
    }

    //get all courses
    @GetMapping("/allcourses")
    public ResponseEntity<PostResponse> getAllCourses(
            @RequestParam(value="pageNumber",defaultValue = appConstants.PAGE_NUMBER, required = false) Integer pagenum,
            @RequestParam(value="pageSize",defaultValue = appConstants.PAGE_SIZE, required = false) Integer pagesize,
            @RequestParam(value="sortBy",defaultValue = appConstants.SORT_BY,required = false)String sortBy,
            @RequestParam(value="sortOrder",defaultValue = appConstants.SORT_ORDER,required = false)String sortOrder
    ){
        PostResponse courseResponse = this.courseService.getAllCourses(pagenum,pagesize,sortBy,sortOrder);
        return new ResponseEntity<>(courseResponse,HttpStatus.OK);
    }


 }
