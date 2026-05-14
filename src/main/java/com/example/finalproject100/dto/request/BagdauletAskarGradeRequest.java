package com.example.finalproject100.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BagdauletAskarGradeRequest {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotNull(message = "Grade is required")
    @Min(value = 0, message = "Grade must be at least 0")
    @Max(value = 100, message = "Grade must be at most 100")
    private Double grade;

    private String semester;
}
