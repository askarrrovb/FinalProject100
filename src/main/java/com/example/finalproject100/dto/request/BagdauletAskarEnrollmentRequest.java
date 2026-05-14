package com.example.finalproject100.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BagdauletAskarEnrollmentRequest {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Course ID is required")
    private Long courseId;
}
