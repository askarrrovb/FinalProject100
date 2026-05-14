package com.example.finalproject100.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BagdauletAskarStudentResponse {

    private Long id;
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate enrollmentDate;
    private LocalDateTime createdAt;
}
