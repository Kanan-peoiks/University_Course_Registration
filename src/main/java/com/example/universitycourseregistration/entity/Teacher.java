package com.example.universitycourseregistration.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;

    @NotNull(message = "Zəhmət olmasa xananı doldurun, boş saxlamaq olmaz.")
    @Column(name = "teacherName", nullable = false)
    @Size(min = 3, max = 15)
    private String name;

    @NotNull(message = "Zəhmət olmasa xananı doldurun, boş saxlamaq olmaz.")
    @Column(name = "teacherSurname", nullable = false)
    @Size(min = 3, max = 25)
    private String surname;

    @NotNull(message = "Zəhmət olmasa xananı doldurun, boş saxlamaq olmaz.")
    @Column(name = "teacherDepartment", nullable = false)
    private String department;

    @OneToMany(mappedBy = "teacher", //course-a baglanmaq ucun elaqe
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Course> courses;
}
