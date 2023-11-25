package com.example.coursespring.create_course.dto;
import com.example.coursespring.create_topic.model.Topic;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class courseDTO {
    private String id;
    private String courseTitle;
    private String description;
    private String email;
     private LocalDate createdAt;


}
