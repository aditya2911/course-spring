package com.example.coursespring.course;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="course")
@NoArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String course_name;

    private String instructor;

    @OneToMany(mappedBy = "course" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Topic> topics = new ArrayList<>();

    private Date createdAt;

    private Date completionAt;

    private Integer durationInWeeks;


}
