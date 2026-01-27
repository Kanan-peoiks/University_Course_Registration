package com.example.universitycourseregistration.service;

import com.example.universitycourseregistration.dto.CourseResponseDto;
import com.example.universitycourseregistration.dto.RegistrationResponseDto;
import com.example.universitycourseregistration.dto.StudentResponseDto;

import java.util.List;

public interface RegistrationService {

    //create
    //registerStudentToCourse -> Student + course
    RegistrationResponseDto registerStudentToCourse(Long studentId, Long courseId);

    //read

    //getCoursesByStudent -> telebenin kurslari
    List<CourseResponseDto> getCoursesByStudent(Long studentId);

    //getStudentsByCourse -> kursun telebeleri
    List<StudentResponseDto> getStudentsByCourse(Long courseId);

    //getCoursesByTeacher -> muellimin kurslari
    List<CourseResponseDto> getCoursesByTeacher(Long teacherId);

    //delete
    //unregisterStudentFromCourse -> telebeden kurs silmek
    void unregisterStudentFromCourse(Long studentId, Long courseId);

    //unregisterTeacherFromCourse -> muellimden kurs silmek
    void unregisterTeacherFromCourse(Long teacherId, Long courseId);

    //isStudentRegistered
    boolean isStudentRegistered(Long studentId, Long courseId);

}
