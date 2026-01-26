package com.example.universitycourseregistration.service;

import com.example.universitycourseregistration.dto.TeacherRequestDto;
import com.example.universitycourseregistration.dto.TeacherResponseDto;
import com.example.universitycourseregistration.entity.Teacher;

import java.util.List;

public interface TeacherService {

    //create
    TeacherResponseDto createTeacher(TeacherRequestDto teacherRequestDto);

    //Read
    List<TeacherResponseDto> getAllTeachers();

    TeacherResponseDto getById(Long id);

    //Update
    TeacherResponseDto updateTeacher(Long id, TeacherRequestDto teacherRequestDto);

    //Delete
    void deleteTeacher(Long id);

    List<TeacherResponseDto> findByDepartment(String department);


}
