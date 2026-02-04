package com.example.universitycourseregistration.controller;

import com.example.universitycourseregistration.dto.StudentRequestDto;
import com.example.universitycourseregistration.dto.StudentResponseDto;
import com.example.universitycourseregistration.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    //CREATE
    @PostMapping
    public StudentResponseDto create(@RequestBody @Valid StudentRequestDto studentRequestDto) {
        return studentService.createStudent(studentRequestDto);
    }

    //READ ALL
    @GetMapping
    public List<StudentResponseDto> getAll(){
        return studentService.getAllStudents();
    }

    //Read By ID
    @GetMapping("/{id:\\d+}")
    public StudentResponseDto getById(@PathVariable Long id){
        return studentService.getById(id);
    }

    //update
    @PutMapping("/{id:\\d+}")
    public StudentResponseDto update(@PathVariable Long id,
                               @RequestBody @Valid StudentRequestDto studentRequestDto) {
        return studentService.updateStudent(id, studentRequestDto);
    }

    //delete
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    //find by email
    @GetMapping("/email")
    public Optional<StudentResponseDto> findByEmail(@RequestParam String email){
        return studentService.findByEmail(email);
    }

    //name + surname
    @GetMapping("/search")
    public List<StudentResponseDto> search(@RequestParam String name,
                                                         @RequestParam String surname){
        return studentService.findByNameAndSurname(name, surname);
    }


}
