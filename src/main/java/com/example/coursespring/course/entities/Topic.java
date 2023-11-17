package com.example.coursespring.course;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="topics")
@NoArgsConstructor
@Getter
@Setter
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    Course course;

    private Date publishedAt;

    private String youtubeLink;

    private String pdfFileMaterial;

}
