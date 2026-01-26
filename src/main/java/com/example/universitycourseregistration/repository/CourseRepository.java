package com.example.universitycourseregistration.repository;

import com.example.universitycourseregistration.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    // Teacher ID-ə görə bütün course-ları tapmaq
    @Query("SELECT c FROM Course c WHERE c.teacher.id = :teacherId")
    List<Course> findByTeacherId(@Param("teacherId") Long teacherId);

    // Course name filter (case-insensitive)
    @Query("SELECT c FROM Course c WHERE LOWER(c.courseName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Course> findByCourseNameContaining(@Param("name") String name);
}
