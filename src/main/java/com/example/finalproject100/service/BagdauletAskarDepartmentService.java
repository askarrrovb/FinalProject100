package com.example.finalproject100.service;

import com.example.finalproject100.dto.request.BagdauletAskarDepartmentRequest;
import com.example.finalproject100.dto.response.BagdauletAskarDepartmentResponse;

import java.util.List;

public interface BagdauletAskarDepartmentService {

    List<BagdauletAskarDepartmentResponse> getAll();

    BagdauletAskarDepartmentResponse getById(Long id);

    BagdauletAskarDepartmentResponse create(BagdauletAskarDepartmentRequest request);

    BagdauletAskarDepartmentResponse update(Long id, BagdauletAskarDepartmentRequest request);

    void delete(Long id);
}
