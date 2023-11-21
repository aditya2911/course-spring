package com.example.coursespring.create_topic.model;

import com.example.coursespring.create_course.models.Course;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@Document(collection = "topics")
public class Topic {
   @Id
   private Integer id;
   private String title;
   private String blog;
   private String email;
   private String dateAdded;
   @ManyToOne
   private Course course;


}
