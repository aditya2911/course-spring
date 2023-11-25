package com.example.coursespring.create_topic.model;

import com.example.coursespring.create_course.models.Course;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "topics")
public class Topic {
   @Id
   @GeneratedValue
   private String id;
   private String title;
   private String blog;
   private String email;
   private LocalDate dateAdded;
   private String courseId;

//   @ManyToOne
//   private Course course;

}
