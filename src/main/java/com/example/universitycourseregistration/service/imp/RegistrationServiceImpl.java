package com.example.universitycourseregistration.service.imp;

import com.example.universitycourseregistration.dto.CourseResponseDto;
import com.example.universitycourseregistration.dto.RegistrationRequestDto;
import com.example.universitycourseregistration.dto.RegistrationResponseDto;
import com.example.universitycourseregistration.dto.StudentResponseDto;
import com.example.universitycourseregistration.entity.Course;
import com.example.universitycourseregistration.entity.Registration;
import com.example.universitycourseregistration.entity.Student;
import com.example.universitycourseregistration.exception.CourseNotFoundException;
import com.example.universitycourseregistration.exception.RegistrationNotFoundException;
import com.example.universitycourseregistration.exception.StudentNotFoundException;
import com.example.universitycourseregistration.repository.CourseRepository;
import com.example.universitycourseregistration.repository.RegistrationRepository;
import com.example.universitycourseregistration.repository.StudentRepository;
import com.example.universitycourseregistration.service.RegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    @Override
    public RegistrationResponseDto registerStudentToCourse(Long studentId, Long courseId) {
        if (isStudentRegistered(studentId, courseId)) {
            throw new RuntimeException("Student is already registered");
        }

        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException("Student not found " + studentId));

        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new CourseNotFoundException("Course not found " + courseId));

        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setCourse(course);
        Registration savedRegistration = registrationRepository.save(registration);

        return mapToDto(savedRegistration);
    }

    @Override
    public List<CourseResponseDto> getCoursesByStudent(Long studentId) {
        List<Registration>  registrations = registrationRepository.findByStudentId(studentId);

        List<CourseResponseDto> courses = new ArrayList<>();

        for (Registration registration : registrations) {
            Course course = registration.getCourse();

            CourseResponseDto dto = new CourseResponseDto();
            dto.setId(course.getId());
            dto.setCourseName(course.getCourseName());
            dto.setCourseDescription(course.getCourseDescription());
            dto.setCredits(course.getCredits());

            if (course.getTeacher() != null){
                dto.setTeacherId(course.getTeacher().getId());
                dto.setTeacherName(course.getTeacher().getName() + " "+ course.getTeacher().getSurname());
            }
            courses.add(dto);
        }
        return courses;
    }

    @Override
    public List<StudentResponseDto> getStudentsByCourse(Long courseId) {
        List <Registration>  registrations = registrationRepository.findByCourseId(courseId);
        List<StudentResponseDto> students = new ArrayList<>();

        for (Registration registration : registrations) {

            Student student = registration.getStudent();

            StudentResponseDto dto = new StudentResponseDto();
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setSurname(student.getSurname());
            dto.setEmail(student.getEmail());

            students.add(dto);
        }
        return students;
    }

    @Override
    public List<CourseResponseDto> getCoursesByTeacher(Long teacherId) {
        List<Course> courses = courseRepository.findByTeacherId(teacherId);
        List<CourseResponseDto> coursesDto = new ArrayList<>();

        for (Course course : courses) {
            CourseResponseDto dto = new CourseResponseDto();
            dto.setId(course.getId());
            dto.setCourseName(course.getCourseName());
            dto.setCourseDescription(course.getCourseDescription());
            dto.setCredits(course.getCredits());

            coursesDto.add(dto);
        }
        return coursesDto;
    }

    @Override
    public void deleteByStudentIdAndCourseId(Long studentId, Long courseId) {
        Registration registration = registrationRepository
                .findByStudentIdAndCourseId(studentId,courseId)
                .orElseThrow(() -> new RegistrationNotFoundException("Registrations not found"));

        registrationRepository.delete(registration);
    }

    @Override
    public void unregisterTeacherFromCourse(Long teacherId, Long courseId) {

    }

    @Override
    public boolean isStudentRegistered(Long studentId, Long courseId) {
        return false;
    }


    //mapping
    private RegistrationResponseDto mapToDto(Registration registration) {
        RegistrationResponseDto registrationResponseDto = new RegistrationResponseDto();
    //id
        registrationResponseDto.setRegistrationId(registration.getRegistrationId());

    //STUDENT
        Student student = registration.getStudent();
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setSurname(student.getSurname());
        studentResponseDto.setEmail(student.getEmail());

    //COURSE
        Course course = registration.getCourse();
        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setId(course.getId());
        courseResponseDto.setCourseName(course.getCourseName());
        courseResponseDto.setCourseDescription(course.getCourseDescription());
        courseResponseDto.setCredits(course.getCredits());

        registrationResponseDto.setStudent(studentResponseDto);
        registrationResponseDto.setCourse(courseResponseDto);

        return registrationResponseDto;
    }

}
