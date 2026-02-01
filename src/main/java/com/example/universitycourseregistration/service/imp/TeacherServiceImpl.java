package com.example.universitycourseregistration.service.imp;

import com.example.universitycourseregistration.dto.CourseResponseDto;
import com.example.universitycourseregistration.dto.TeacherRequestDto;
import com.example.universitycourseregistration.dto.TeacherResponseDto;
import com.example.universitycourseregistration.entity.Course;
import com.example.universitycourseregistration.entity.Teacher;
import com.example.universitycourseregistration.exception.TeacherNotFoundException;
import com.example.universitycourseregistration.repository.TeacherRepository;
import com.example.universitycourseregistration.service.TeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;


    @Override
    public TeacherResponseDto createTeacher(TeacherRequestDto teacherRequestDto) {
        Teacher teacher = mapToEntity(teacherRequestDto);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return mapToDto(savedTeacher);
    }

    @Override
    public List<TeacherResponseDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherResponseDto> teacherResponseDtos = new ArrayList<>();

        for  (Teacher teacher : teachers) {
            teacherResponseDtos.add(mapToDto(teacher));
        }
        return teacherResponseDtos;
    }

    @Override
    public TeacherResponseDto getById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() ->
                new TeacherNotFoundException("Teacher with id " + id + " not found."));
        return mapToDto(teacher);
    }

    @Override
    public TeacherResponseDto updateTeacher(Long id, TeacherRequestDto teacherRequestDto) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() ->
                new  TeacherNotFoundException("Teacher with id " + id + " not found."));

        teacher.setName(teacherRequestDto.getName());
        teacher.setSurname(teacherRequestDto.getSurname());
        teacher.setDepartment(teacherRequestDto.getDepartment());

        Teacher updated = teacherRepository.save(teacher);
        return mapToDto(updated);
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() ->
                new  TeacherNotFoundException("Teacher with id " + id + " not found."));
        teacherRepository.delete(teacher);
    }

    @Override
    public List<TeacherResponseDto> findByDepartment(String department) {
        List<Teacher> teachers = teacherRepository.findByDepartment(department);
        List<TeacherResponseDto> teacherResponseDtos = new ArrayList<>();
        for  (Teacher teacher : teachers) {
            teacherResponseDtos.add(mapToDto(teacher));
        }
        return teacherResponseDtos;
    }

    //mapping

    private Teacher mapToEntity (TeacherRequestDto teacherRequestDto ) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherRequestDto.getName());
        teacher.setSurname(teacherRequestDto.getSurname());
        teacher.setDepartment(teacherRequestDto.getDepartment());
        return teacher;
    }

    private TeacherResponseDto mapToDto(Teacher teacher ) {
        TeacherResponseDto teacherResponseDto = new TeacherResponseDto();

        teacherResponseDto.setId(teacher.getId());
        teacherResponseDto.setName(teacher.getName());
        teacherResponseDto.setSurname(teacher.getSurname());
        teacherResponseDto.setDepartment(teacher.getDepartment());

        List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();

        if (teacher.getCourses() != null) {
            for (Course course : teacher.getCourses()) {
                CourseResponseDto courseResponseDto = new CourseResponseDto();

                courseResponseDto.setId(course.getId());
                courseResponseDto.setCourseName(course.getCourseName());
                courseResponseDto.setCourseDescription(course.getCourseDescription());
                courseResponseDto.setCredits(course.getCredits());

                courseResponseDto.setTeacherId(teacher.getId());
                courseResponseDto.setTeacherName(teacher.getName()
                        + " " + teacher.getSurname());

                courseResponseDtoList.add(courseResponseDto);
            }
        }
        teacherResponseDto.setCourses(courseResponseDtoList);
        return teacherResponseDto;
    }

}
