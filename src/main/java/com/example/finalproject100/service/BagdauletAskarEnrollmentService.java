package com.example.finalproject100.service;

import com.example.finalproject100.dto.response.BagdauletAskarEnrollmentResponse;

import java.util.List;

public interface BagdauletAskarEnrollmentService {

    List<BagdauletAskarEnrollmentResponse> getAll();

    BagdauletAskarEnrollmentResponse getById(Long id);

    BagdauletAskarEnrollmentResponse enrollStudent(Long studentId, Long courseId);

    BagdauletAskarEnrollmentResponse updateStatus(Long id, String status);

    void delete(Long id);

    List<BagdauletAskarEnrollmentResponse> getByStudentId(Long studentId);
}
