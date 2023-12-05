package com.example.coursespring.create_course.models;


import com.example.coursespring.create_topic.model.Topic;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document("courses")
@AllArgsConstructor
@NoArgsConstructor
@Data
 public class Course {

    LocalDate localDate;



    @Id
@GeneratedValue
    private String id;
    private String courseTitle;
    private String description;
    private String email;
      private LocalDate createdAt;
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    private List<Topic> topics = new ArrayList<>();



}
