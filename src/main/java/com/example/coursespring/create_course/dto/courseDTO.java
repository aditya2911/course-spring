package com.example.coursespring.create_course.dto;
import com.example.coursespring.create_topic.model.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Date createdAt;
}
