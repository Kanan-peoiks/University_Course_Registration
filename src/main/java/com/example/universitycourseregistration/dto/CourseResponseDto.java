package com.example.universitycourseregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {
    private long id;
    private String courseName;
    private String courseDescription;
    private int credits;
    private Long teacherId;
    private String teacherName;
    private List<StudentResponseDto> students;
}
