package com.example.coursespring.create_topic.dto;

import com.example.coursespring.create_course.models.Course;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class topicDTO {
    private String id;
    private String title;
    private String blog;
    private String email;
    private Date dateAdded;
    private String courseId;
}

