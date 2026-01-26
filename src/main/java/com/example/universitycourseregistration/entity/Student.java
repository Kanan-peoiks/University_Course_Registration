package com.example.universitycourseregistration.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Zəhmət olmasa xananı doldurun, boş saxlamaq olmaz.")
    @Column(name = "Name", nullable = false)
    @Size(min = 3, max = 15)
    private String name;

    @NotNull(message = "Zəhmət olmasa xananı doldurun, boş saxlamaq olmaz.")
    @Column(name = "Surname", nullable = false)
    @Size(min = 3, max = 25)
    private String surname;

    @Email
    @NotNull(message = "Zəhmət olmasa xananı doldurun, boş saxlamaq olmaz.")
    @Column(name = "Email", nullable = false, unique = true)
    private String email;

}
