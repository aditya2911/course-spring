package com.example.coursespring.create_topic.dto;

import com.example.coursespring.create_course.models.Course;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class topicDTO {
    private String id;
    private String title;
    private String blog;
    private String courseID;
    private String email;
    private String dateAdded;
    private Course course;
}
