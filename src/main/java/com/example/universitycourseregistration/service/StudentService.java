package com.example.universitycourseregistration.service;

import com.example.universitycourseregistration.dto.StudentRequestDto;
import com.example.universitycourseregistration.dto.StudentResponseDto;
import com.example.universitycourseregistration.entity.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    //create
    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);

    //read
    List<StudentResponseDto> getAllStudents();

    StudentResponseDto getById(Long studentId);

    //update
    StudentResponseDto updateStudent(Long studentId, StudentRequestDto studentRequestDto);

    //Delete
    void deleteStudent(Long studentId);

    Optional<StudentResponseDto> findByEmail(String email);

    List<StudentResponseDto> findByNameAndSurname(String name,String surname);

}
