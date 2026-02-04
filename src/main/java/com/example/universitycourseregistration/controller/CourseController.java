package com.example.universitycourseregistration.controller;

import com.example.universitycourseregistration.dto.CourseRequestDto;
import com.example.universitycourseregistration.dto.CourseResponseDto;
import com.example.universitycourseregistration.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    //create
    @PostMapping
    public CourseResponseDto create(@RequestBody @Valid CourseRequestDto dto){
    return courseService.createCourse(dto);
    }

    //read all
    @GetMapping
    public List<CourseResponseDto> getAll(){
        return courseService.getAllCourses();
    }

    //read by id
    @GetMapping("/{id}")
    public CourseResponseDto getById(@PathVariable Long id){
        return courseService.getById(id);
    }

    //update
    @PutMapping("/{id}")
    public CourseResponseDto update(@PathVariable Long id,
                                    @RequestBody @Valid CourseRequestDto dto) {
        return courseService.updateCourse(id, dto);
    }

    //delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        courseService.deleteCourse(id);
    }

    //find by teacher id
    @GetMapping("/teacher/{teacherId}")
    public List<CourseResponseDto> byTeacher(@PathVariable Long teacherId){
        return courseService.findByTeacherId(teacherId);
    }

    //find by teacher department
    @GetMapping("/department")
    public List<CourseResponseDto> byDepartment(@RequestParam String department){
        return courseService.findByTeacherDepartment(department);
    }

    //find by course name
    @GetMapping("/search")
    public List<CourseResponseDto> byName(@RequestParam String courseName){
        return courseService.findByCourseNameContaining(courseName);
    }
}
