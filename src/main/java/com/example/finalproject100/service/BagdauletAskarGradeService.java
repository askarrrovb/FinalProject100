package com.example.finalproject100.service;

import com.example.finalproject100.dto.request.BagdauletAskarGradeRequest;
import com.example.finalproject100.dto.response.BagdauletAskarGradeResponse;

import java.util.List;

public interface BagdauletAskarGradeService {

    List<BagdauletAskarGradeResponse> getAll();

    BagdauletAskarGradeResponse getById(Long id);

    BagdauletAskarGradeResponse create(BagdauletAskarGradeRequest request);

    BagdauletAskarGradeResponse update(Long id, BagdauletAskarGradeRequest request);

    void delete(Long id);

    List<BagdauletAskarGradeResponse> getByStudentId(Long studentId);
}
