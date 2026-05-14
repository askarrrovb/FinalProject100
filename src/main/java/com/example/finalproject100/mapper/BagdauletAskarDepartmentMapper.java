package com.example.finalproject100.mapper;

import com.example.finalproject100.dto.request.BagdauletAskarDepartmentRequest;
import com.example.finalproject100.dto.response.BagdauletAskarDepartmentResponse;
import com.example.finalproject100.entity.BagdauletAskarDepartment;

public class BagdauletAskarDepartmentMapper {

    public static BagdauletAskarDepartmentResponse toResponse(BagdauletAskarDepartment department) {
        BagdauletAskarDepartmentResponse response = new BagdauletAskarDepartmentResponse();
        response.setId(department.getId());
        response.setDepartmentName(department.getDepartmentName());
        response.setDepartmentCode(department.getDepartmentCode());
        response.setDescription(department.getDescription());
        response.setHead(department.getHead());
        response.setCreatedAt(department.getCreatedAt());
        return response;
    }

    public static BagdauletAskarDepartment toEntity(BagdauletAskarDepartmentRequest request) {
        BagdauletAskarDepartment department = new BagdauletAskarDepartment();
        department.setDepartmentName(request.getDepartmentName());
        department.setDepartmentCode(request.getDepartmentCode());
        department.setDescription(request.getDescription());
        department.setHead(request.getHead());
        return department;
    }
}
