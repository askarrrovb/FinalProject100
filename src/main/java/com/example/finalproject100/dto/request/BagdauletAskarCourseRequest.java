package com.example.finalproject100.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BagdauletAskarCourseRequest {

    @NotBlank(message = "Course name is required")
    private String courseName;

    @NotBlank(message = "Course code is required")
    private String courseCode;

    @NotNull(message = "Credits is required")
    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 6, message = "Credits must be at most 6")
    private Integer credits;

    private String description;

    private Long teacherId;

    private Long departmentId;
}
