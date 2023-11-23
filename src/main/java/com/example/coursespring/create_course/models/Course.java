package com.example.coursespring.create_course.models;


import com.example.coursespring.create_topic.model.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document("courses")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    @Id
    private String id;
    private String courseTitle;
    private String description;
    private String email;
    private Date createdAt;
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    private List<Topic> topics = new ArrayList<>();
}
