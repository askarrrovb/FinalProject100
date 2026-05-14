package com.example.finalproject100.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BagdauletAskarDepartmentResponse {

    private Long id;
    private String departmentName;
    private String departmentCode;
    private String description;
    private String head;
    private LocalDateTime createdAt;
}
