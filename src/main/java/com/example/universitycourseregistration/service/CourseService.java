package com.example.universitycourseregistration.service;

import com.example.universitycourseregistration.dto.CourseRequestDto;
import com.example.universitycourseregistration.dto.CourseResponseDto;

import java.util.List;

public interface CourseService {

    //create
    CourseResponseDto createCourse(CourseRequestDto courseRequestDto);

    //Read
    List<CourseResponseDto> getAllCourses();

    CourseResponseDto getById(Long id);

    //update
    CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto);

    //delete
    void deleteCourse(Long id);

    List<CourseResponseDto> findByTeacherId(Long teacherId);

    List<CourseResponseDto> findByTeacherDepartment(String department);

    List<CourseResponseDto> findByCourseNameContaining(String courseName);

    //Adinda "java" olan butun kurslar: "java", "java advanced"


}
