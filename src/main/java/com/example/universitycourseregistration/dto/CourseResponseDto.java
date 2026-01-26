package com.example.universitycourseregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {
    private long id;
    private String courseName;
    private String courseCode;
    private String courseDescription;
}
