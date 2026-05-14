package com.example.finalproject100.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BagdauletAskarEnrollmentResponse {

    private Long id;
    private String studentName;
    private String courseName;
    private String status;
    private LocalDateTime enrollmentDate;
}
