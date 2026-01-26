package com.example.universitycourseregistration.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @NotNull(message = "Zəhmət olmasa xananı doldurun, boş saxlamaq olmaz.")
    @Column(name = "courseName")
    @Size(min = 1, max = 50)
    private String courseName;

    @NotEmpty(message = "Zəhmət olmasa xananı doldurun.")
    @Column (name = "courseDescription")
    private String courseDescription;

    @NotNull(message = "Zəhmət olmasa xananı doldurun, boş saxlamaq olmaz.")
    @Column (name = "credits")
    private int credits;

    @ManyToOne
    @JoinColumn(name = "teacher_id") //teacher ile elaqe
    private Teacher teacher;

    @OneToMany(mappedBy = "course", //registrations-a baglanmaq ucun elaqe
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Registration> registrations;

}
