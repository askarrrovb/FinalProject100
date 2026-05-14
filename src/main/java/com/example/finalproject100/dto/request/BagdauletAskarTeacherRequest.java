package com.example.finalproject100.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BagdauletAskarTeacherRequest {

    @NotBlank(message = "Teacher ID is required")
    private String teacherId;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String phone;

    private String department;

    @Positive(message = "Salary must be positive")
    private Double salary;
}
