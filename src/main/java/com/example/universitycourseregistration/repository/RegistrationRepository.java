package com.example.universitycourseregistration.repository;

import com.example.universitycourseregistration.entity.Registration;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Long> {
    // Student-in bütün registration-ları
    @Query("SELECT r FROM Registration r WHERE r.student.id = :studentId")
    List<Registration> findByStudentId(@Param("studentId") Long studentId);

    // Course-un bütün student-ları
    @Query("SELECT r FROM Registration r WHERE r.course.id = :courseId")
    List<Registration> findByCourseId(@Param("courseId") Long courseId);

    // Student artıq course-a yazılıb mı?
    @Query("SELECT r FROM Registration r WHERE r.student.id = :studentId AND r.course.id = :courseId")
    Optional<Registration> findByStudentIdAndCourseId(@Param("studentId") Long studentId,
                                                      @Param("courseId") Long courseId);

    // registration sil
    @Modifying
    @Transactional
    @Query("DELETE FROM Registration r WHERE r.student.id = :studentId AND r.course.id = :courseId")
    void deleteByStudentIdAndCourseId(@Param("studentId") Long studentId,
                                      @Param("courseId") Long courseId);




}
