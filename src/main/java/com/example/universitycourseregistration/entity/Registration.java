package com.example.universitycourseregistration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Registration")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private Long registrationId;

    @ManyToOne //Student ile elaqe
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne //course ile elaqe
    @JoinColumn(name = "course_id")
    private Course course;

}
