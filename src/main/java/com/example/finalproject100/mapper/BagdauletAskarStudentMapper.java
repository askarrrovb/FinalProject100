package com.example.finalproject100.mapper;

import com.example.finalproject100.dto.request.BagdauletAskarStudentRequest;
import com.example.finalproject100.dto.response.BagdauletAskarStudentResponse;
import com.example.finalproject100.entity.BagdauletAskarStudent;

public class BagdauletAskarStudentMapper {

    public static BagdauletAskarStudentResponse toResponse(BagdauletAskarStudent student) {
        BagdauletAskarStudentResponse response = new BagdauletAskarStudentResponse();
        response.setId(student.getId());
        response.setStudentId(student.getStudentId());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        response.setPhone(student.getPhone());
        response.setEnrollmentDate(student.getEnrollmentDate());
        response.setCreatedAt(student.getCreatedAt());
        return response;
    }

    public static BagdauletAskarStudent toEntity(BagdauletAskarStudentRequest request) {
        BagdauletAskarStudent student = new BagdauletAskarStudent();
        student.setStudentId(request.getStudentId());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setEnrollmentDate(request.getEnrollmentDate());
        return student;
    }
}
