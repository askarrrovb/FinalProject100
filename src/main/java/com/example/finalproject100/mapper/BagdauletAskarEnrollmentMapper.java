package com.example.finalproject100.mapper;

import com.example.finalproject100.dto.response.BagdauletAskarEnrollmentResponse;
import com.example.finalproject100.entity.BagdauletAskarEnrollment;

public class BagdauletAskarEnrollmentMapper {

    public static BagdauletAskarEnrollmentResponse toResponse(BagdauletAskarEnrollment enrollment) {
        BagdauletAskarEnrollmentResponse response = new BagdauletAskarEnrollmentResponse();
        response.setId(enrollment.getId());
        response.setStatus(enrollment.getStatus());
        response.setEnrollmentDate(enrollment.getEnrollmentDate());
        if (enrollment.getStudent() != null) {
            response.setStudentName(enrollment.getStudent().getFirstName() + " " + enrollment.getStudent().getLastName());
        }
        if (enrollment.getCourse() != null) {
            response.setCourseName(enrollment.getCourse().getCourseName());
        }
        return response;
    }
}
