package com.example.universitycourseregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String department;
    private List<CourseResponseDto> courses;
}
