package com.example.finalproject100.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BagdauletAskarTeacherResponse {

    private Long id;
    private String teacherId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
    private Double salary;
    private LocalDateTime createdAt;
}
