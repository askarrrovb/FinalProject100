package com.example.finalproject100.service;

import com.example.finalproject100.dto.request.BagdauletAskarTeacherRequest;
import com.example.finalproject100.dto.response.BagdauletAskarTeacherResponse;

import java.util.List;

public interface BagdauletAskarTeacherService {

    List<BagdauletAskarTeacherResponse> getAll();

    BagdauletAskarTeacherResponse getById(Long id);

    BagdauletAskarTeacherResponse create(BagdauletAskarTeacherRequest request);

    BagdauletAskarTeacherResponse update(Long id, BagdauletAskarTeacherRequest request);

    void delete(Long id);
}
