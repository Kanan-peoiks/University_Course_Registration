package com.example.universitycourseregistration.controller;

import com.example.universitycourseregistration.dto.TeacherRequestDto;
import com.example.universitycourseregistration.dto.TeacherResponseDto;
import com.example.universitycourseregistration.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    //CREATE
    @PostMapping
    public TeacherResponseDto create(@RequestBody @Valid TeacherRequestDto dto) {
        return teacherService.createTeacher(dto);
    }

    //Read all
    @GetMapping
    public List<TeacherResponseDto> getAll() {
        return teacherService.getAllTeachers();
    }

    //read by ID
    @GetMapping("/{id:\\d+}")
    public TeacherResponseDto getById(@PathVariable Long id) {
        return teacherService.getById(id);
    }

    //update
    @PutMapping("/{id:\\d+}")
    public TeacherResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid TeacherRequestDto dto) {
        return teacherService.updateTeacher(id, dto);
    }


    //delete
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

    //find By Department
    @GetMapping("/department")
    public List<TeacherResponseDto> findByDepartment(@RequestParam String department) {
        return teacherService.findByDepartment(department);
    }

}
