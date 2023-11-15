package com.example.coursespring.create_course.models;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("courses")
@AllArgsConstructor
@Data
public class Course {
    @Id
    private String id;
    private String courseTitle;
    private String description;
}
