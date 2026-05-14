package com.example.finalproject100.mapper;

import com.example.finalproject100.dto.request.BagdauletAskarTeacherRequest;
import com.example.finalproject100.dto.response.BagdauletAskarTeacherResponse;
import com.example.finalproject100.entity.BagdauletAskarTeacher;

public class BagdauletAskarTeacherMapper {

    public static BagdauletAskarTeacherResponse toResponse(BagdauletAskarTeacher teacher) {
        BagdauletAskarTeacherResponse response = new BagdauletAskarTeacherResponse();
        response.setId(teacher.getId());
        response.setTeacherId(teacher.getTeacherId());
        response.setFirstName(teacher.getFirstName());
        response.setLastName(teacher.getLastName());
        response.setEmail(teacher.getEmail());
        response.setPhone(teacher.getPhone());
        response.setDepartment(teacher.getDepartment());
        response.setSalary(teacher.getSalary());
        response.setCreatedAt(teacher.getCreatedAt());
        return response;
    }

    public static BagdauletAskarTeacher toEntity(BagdauletAskarTeacherRequest request) {
        BagdauletAskarTeacher teacher = new BagdauletAskarTeacher();
        teacher.setTeacherId(request.getTeacherId());
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setPhone(request.getPhone());
        teacher.setDepartment(request.getDepartment());
        teacher.setSalary(request.getSalary());
        return teacher;
    }
}
