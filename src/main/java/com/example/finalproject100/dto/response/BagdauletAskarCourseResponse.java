package com.example.finalproject100.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BagdauletAskarCourseResponse {

    private Long id;
    private String courseName;
    private String courseCode;
    private Integer credits;
    private String description;
    private String teacherName;
    private String departmentName;
    private LocalDateTime createdAt;
}
