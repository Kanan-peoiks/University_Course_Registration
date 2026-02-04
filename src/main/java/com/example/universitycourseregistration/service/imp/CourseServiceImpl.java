package com.example.universitycourseregistration.service.imp;

import com.example.universitycourseregistration.dto.CourseRequestDto;
import com.example.universitycourseregistration.dto.CourseResponseDto;
import com.example.universitycourseregistration.dto.RegistrationResponseDto;
import com.example.universitycourseregistration.dto.StudentResponseDto;
import com.example.universitycourseregistration.entity.Course;
import com.example.universitycourseregistration.entity.Registration;
import com.example.universitycourseregistration.entity.Student;
import com.example.universitycourseregistration.entity.Teacher;
import com.example.universitycourseregistration.exception.CourseNotFoundException;
import com.example.universitycourseregistration.exception.TeacherNotFoundException;
import com.example.universitycourseregistration.repository.CourseRepository;
import com.example.universitycourseregistration.repository.TeacherRepository;
import com.example.universitycourseregistration.service.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        Teacher teacher = teacherRepository.findById(courseRequestDto.getTeacherId()).orElseThrow(() ->
                new TeacherNotFoundException("Teacher not found " + courseRequestDto.getTeacherId()));

        Course course = mapToEntity(courseRequestDto);
        course.setTeacher(teacher);

        Course savedCourse = courseRepository.save(course);
        return mapToDto(savedCourse);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponseDto> result = new ArrayList<>();

        for (Course course : courses) {
            result.add(mapToDto(course));
        }
        return result;
    }

    @Override
    public CourseResponseDto getById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new CourseNotFoundException("Course not found " + id));
        return mapToDto(course);
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new CourseNotFoundException("Course with id " + id + " not found."));

        course.setCourseName(courseRequestDto.getCourseName());
        course.setCourseDescription(courseRequestDto.getCourseDescription());
        course.setCredits(courseRequestDto.getCredits());

        //teacher
        if (!course.getTeacher().getId().equals(courseRequestDto.getTeacherId())) {
            Teacher teacher = teacherRepository.findById(courseRequestDto.getTeacherId())
                    .orElseThrow(() -> new TeacherNotFoundException(
                            "Teacher not found: " + courseRequestDto.getTeacherId()));
            course.setTeacher(teacher);
        }
        Course savedCourse = courseRepository.save(course);

        return mapToDto(savedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new CourseNotFoundException("Course with id " + id + " not found."));
        courseRepository.delete(course);
    }

    @Override
    public List<CourseResponseDto> findByTeacherId(Long teacherId) {
        List<Course> courses = courseRepository.findByTeacherId(teacherId);

        List<CourseResponseDto> result = new ArrayList<>();
        for (Course c : courses) {
            result.add(mapToDto(c));
        }
        return result;
    }

    @Override
    public List<CourseResponseDto> findByTeacherDepartment(String department) {
        List<Course> allCourses = courseRepository.findAll();
        List<CourseResponseDto> result = new ArrayList<>();
        for (Course c : allCourses) {
            if (c.getTeacher() != null && department.equalsIgnoreCase(c.getTeacher().getDepartment())) {
                result.add(mapToDto(c));
            }
        }
        return result;
    }

    @Override
    public List<CourseResponseDto> findByCourseNameContaining(String courseName) {
        List<Course> allCourses = courseRepository.findByCourseNameContaining(courseName);
        List<CourseResponseDto> result = new ArrayList<>();
        for (Course c : allCourses) {
            result.add(mapToDto(c));
        }
        return result;
    }

    //mapping
    private Course mapToEntity(CourseRequestDto courseRequestDto) {
        Course course = new Course();
        course.setCourseName(courseRequestDto.getCourseName());
        course.setCourseDescription(courseRequestDto.getCourseDescription());
        course.setCredits(courseRequestDto.getCredits());
        return course;
    }

    private CourseResponseDto mapToDto(Course course) {
        CourseResponseDto dto = new CourseResponseDto();
        dto.setId(course.getId());
        dto.setCourseName(course.getCourseName());
        dto.setCourseDescription(course.getCourseDescription());
        dto.setCredits(course.getCredits());

        if (course.getTeacher() != null) {
            dto.setTeacherId(course.getTeacher().getId());
            dto.setTeacherName(course.getTeacher().getName()
                    + " " + course.getTeacher().getSurname());
        }

        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
        if (course.getRegistrations() != null) {
            for (Registration reg : course.getRegistrations()) {
                Student student = reg.getStudent();

                StudentResponseDto studentResponseDto = new StudentResponseDto();
                studentResponseDto.setId(student.getId());
                studentResponseDto.setName(student.getName());
                studentResponseDto.setSurname(student.getSurname());
                studentResponseDto.setEmail(student.getEmail());

                studentResponseDtoList.add(studentResponseDto);

            }
            dto.setStudents(studentResponseDtoList);
        }
        return dto;
    }
}
