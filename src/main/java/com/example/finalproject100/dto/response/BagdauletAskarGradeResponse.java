package com.example.finalproject100.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BagdauletAskarGradeResponse {

    private Long id;
    private String studentName;
    private String courseName;
    private Double grade;
    private String letterGrade;
    private String semester;
    private LocalDateTime gradedAt;
}
