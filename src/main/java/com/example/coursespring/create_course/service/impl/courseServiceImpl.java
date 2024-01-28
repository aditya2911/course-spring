package com.example.coursespring.create_course.service.impl;

import com.example.coursespring.create_course.dto.courseDTO;
import com.example.coursespring.create_course.models.Course;
import com.example.coursespring.create_course.repository.CourseRepository;

import com.example.coursespring.create_course.service.courseService;
import com.example.coursespring.create_topic.repositories.TopicRepository;
import com.example.coursespring.exceptions.ResourceNotFoundException;
import com.example.coursespring.response.PostResponse;
import com.example.coursespring.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
    public courseDTO createCourse(courseDTO coursedto) {
        Course course = this.modelmapper.map(coursedto,Course.class);
        course.setCourseTitle(coursedto.getCourseTitle());
        course.setDescription(coursedto.getDescription());
        course.setEmail(coursedto.getEmail());
        course.setCreatedAt(LocalDate.now());

        Course newCourse = this.cRepo.save(course);
        return this.modelmapper.map(newCourse,courseDTO.class);
    }


    @Override
    public courseDTO updateCourse(courseDTO courseDTO, String cid) {
        Course course = this.cRepo.findById(cid)
                .orElseThrow(() -> new ResourceNotFoundException("Course","Id",cid));
        course.setCourseTitle(courseDTO.getCourseTitle());
        course.setDescription(courseDTO.getDescription());

        Course updateCourse = this.cRepo.save(course);
        return this.modelmapper.map(updateCourse, courseDTO.class);
    }

 @Override
     public List<courseDTO> getCoursesByEmail(String email) {


        List<Course> course = this.cRepo.findByEmail(email);
        ArrayList<courseDTO> listOfCourses = new ArrayList<courseDTO>() ;
     for (Course value : course) {
         courseDTO temp = this.modelmapper.map(value, courseDTO.class);
         listOfCourses.add(temp);

     }
        return  listOfCourses;
    }

    @Override
    public void deleteCourse(String cid) {
        Course course = this.cRepo.findById(cid)
                .orElseThrow(() -> new ResourceNotFoundException("Course","Id",cid));
        this.cRepo.delete(course);
    }

    @Override
    public PostResponse getAllCourses(Integer pagenumber, Integer size, String sortBy, String sortOrder) {
        Sort sort = (sortOrder.equalsIgnoreCase("asc"))?sort = Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
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
    public courseDTO getCourseById(String cid) {
        Course course = this.cRepo.findById(cid)
                .orElseThrow(() -> new ResourceNotFoundException("Post","Id",cid));
        return this.modelmapper.map(course, courseDTO.class);
    }

    @Override
    public List<courseDTO> getAllCoursesByUser(String uid) {
        return null;
    }

    @Override
    public List<courseDTO> searchCourse(String search) {
        return null;
    }
}
