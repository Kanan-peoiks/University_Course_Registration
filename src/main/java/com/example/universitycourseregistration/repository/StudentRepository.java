package com.example.universitycourseregistration.repository;

import com.example.universitycourseregistration.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    // Email-ə görə student tapmaq
    Optional<Student> findByEmail(String email);

    // Ad və soyad üzrə axtarış
    @Query("SELECT s FROM Student s WHERE s.name = :name AND s.surname = :surname")
    Optional<Student> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
}
