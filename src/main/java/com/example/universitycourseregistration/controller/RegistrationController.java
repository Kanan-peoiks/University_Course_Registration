package com.example.universitycourseregistration.controller;

import com.example.universitycourseregistration.dto.CourseResponseDto;
import com.example.universitycourseregistration.dto.RegistrationResponseDto;
import com.example.universitycourseregistration.dto.StudentResponseDto;
import com.example.universitycourseregistration.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/registrations"))
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    //create student +course
    @PostMapping
    public RegistrationResponseDto register(@RequestParam Long studentId,
                                            @RequestParam Long courseId){
        return registrationService.registerStudentToCourse(studentId, courseId);
    }

    //read
    // student`s courses
    @GetMapping("/student/{studentId}/courses")
    public List<CourseResponseDto> getCoursesByStudent(@PathVariable Long studentId){
        return registrationService.getCoursesByStudent(studentId);
    }

    // course students
    @GetMapping("/course/{courseId}/students")
    public List<StudentResponseDto> getStudents(@PathVariable Long courseId){
        return registrationService.getStudentsByCourse(courseId);
    }

    // teacher`s courses
    @GetMapping("/teacher/{teacherId}/courses")
    public List<CourseResponseDto> getCoursesByTeacher(@PathVariable Long teacherId){
        return registrationService.getCoursesByTeacher(teacherId);
    }

    //Is the student enrolled in the course?
    @GetMapping("/student/registered")
    public boolean isStudentRegistered(@RequestParam Long studentId,
                                       @RequestParam Long courseId) {
        return registrationService.isStudentRegistered(studentId, courseId);
    }

    //delete
    // delete student`s course
    @DeleteMapping("/students")
    public void deleteStudentsCourse (@RequestParam Long studentId,
                        @RequestParam Long courseId){
        registrationService.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    //delete teacher`s course
    @DeleteMapping("/teachers")
    public void deleteTeachersCourse(@RequestParam Long teacherId,
                                     @RequestParam Long courseId){
        registrationService.unregisterTeacherFromCourse(teacherId, courseId);
    }




}
