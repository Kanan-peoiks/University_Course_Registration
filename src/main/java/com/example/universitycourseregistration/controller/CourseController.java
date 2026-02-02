package com.example.universitycourseregistration.controller;

import com.example.universitycourseregistration.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
}
