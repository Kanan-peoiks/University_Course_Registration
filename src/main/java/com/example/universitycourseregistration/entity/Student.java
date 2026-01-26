package com.example.universitycourseregistration.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @NotNull(message = "Zəhmət olmasa xananı doldurun, boş saxlamaq olmaz.")
    @Column(name = "studentName", nullable = false)
    @Size(min = 3, max = 15)
    private String name;

    @NotNull(message = "Zəhmət olmasa xananı doldurun, boş saxlamaq olmaz.")
    @Column(name = "studentSurname", nullable = false)
    @Size(min = 3, max = 25)
    private String surname;

    @Email
    @NotNull(message = "Zəhmət olmasa xananı doldurun, boş saxlamaq olmaz.")
    @Column(name = "studentEmail", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "student", //regostrations-a baglanmaq ucun elaqe
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Registration> registrations;
}
