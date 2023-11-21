package com.example.coursespring.create_course.response;

import com.example.coursespring.create_course.dto.courseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CourseResponse {
        private List<courseDTO> content;
        private int pageNumber;
        private int pageSize;
        private long totalElements;
        private int totalPages;
        private boolean lastPage;
}
