package com.example.universitycourseregistration.service.imp;

import com.example.universitycourseregistration.dto.CourseResponseDto;
import com.example.universitycourseregistration.dto.StudentRequestDto;
import com.example.universitycourseregistration.dto.StudentResponseDto;
import com.example.universitycourseregistration.entity.Course;
import com.example.universitycourseregistration.entity.Registration;
import com.example.universitycourseregistration.entity.Student;
import com.example.universitycourseregistration.exception.StudentNotFoundException;
import com.example.universitycourseregistration.repository.StudentRepository;
import com.example.universitycourseregistration.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        Student student = mapToEntity(studentRequestDto);
        Student saved  = studentRepository.save(student);
        return mapToDto(saved);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        List<StudentResponseDto> result = new ArrayList<>();

        for (Student student : students) {
            result.add(mapToDto(student));
        }
        return result;
    }

    @Override
    public StudentResponseDto getById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
        new StudentNotFoundException("Student with id: " + studentId + " not found"));

        return mapToDto(student);
    }

    @Override
    public StudentResponseDto updateStudent(Long studentId, StudentRequestDto studentRequestDto) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException("Student with id: " + studentId + " not found"));

        student.setName(studentRequestDto.getName());
        student.setSurname(studentRequestDto.getSurname());
        student.setEmail(studentRequestDto.getEmail());

        Student updated = studentRepository.save(student);

        return mapToDto(updated);
    }

    @Override
    public void deleteStudent(Long studentId) {
    Student student = studentRepository.findById(studentId).orElseThrow(()->
            new StudentNotFoundException("Student with id: " + studentId + " not found"));

    studentRepository.delete(student);
    }

    @Override
    public Optional<StudentResponseDto> findByEmail(String email) {
        return studentRepository.findByEmail(email)
                .map(this :: mapToDto);
    }

    @Override
    public List<StudentResponseDto> findByNameAndSurname(String name, String surname) {
        Optional<Student> optional = studentRepository.findByNameAndSurname(name, surname);
        List<StudentResponseDto> list = new ArrayList<>();
        optional.ifPresent(student -> list.add(mapToDto(student)));
        return list;
    }

    //mapping

    private Student mapToEntity(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setSurname(studentRequestDto.getSurname());
        student.setEmail(studentRequestDto.getEmail());
        return student;
    }

    private StudentResponseDto mapToDto(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setSurname(student.getSurname());
        studentResponseDto.setEmail(student.getEmail());

        List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();

        if (student.getRegistrations() != null) {
            for (Registration registration : student.getRegistrations()) {

                Course c = registration.getCourse();
                CourseResponseDto courseResponseDto = new CourseResponseDto();

                courseResponseDto.setId(c.getId());
                courseResponseDto.setCourseName(c.getCourseName());
                courseResponseDto.setCourseDescription(c.getCourseDescription());
                courseResponseDto.setCredits(c.getCredits());

                if (c.getTeacher() != null) {
                    courseResponseDto.setTeacherId(c.getTeacher().getId());
                    courseResponseDto.setTeacherName(
                            c.getTeacher().getName() + " " + c.getTeacher().getSurname());
                }
                courseResponseDtoList.add(courseResponseDto);
            }
        }
        studentResponseDto.setCourses(courseResponseDtoList);

        return studentResponseDto;
    }
}
