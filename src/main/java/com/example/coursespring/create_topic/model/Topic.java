package com.example.coursespring.create_topic.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@Document(collection = "topics")
public class Topic {
   @Id
   private String id;
   private String title;
   private String blog;
   private String courseID;
   private String email;
   private String dateAdded;



}
