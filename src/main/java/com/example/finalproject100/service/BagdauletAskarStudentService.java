package com.example.finalproject100.service;

import com.example.finalproject100.dto.request.BagdauletAskarStudentRequest;
import com.example.finalproject100.dto.response.BagdauletAskarStudentResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BagdauletAskarStudentService {

    List<BagdauletAskarStudentResponse> getAll();

    BagdauletAskarStudentResponse getById(Long id);

    BagdauletAskarStudentResponse create(BagdauletAskarStudentRequest request);

    BagdauletAskarStudentResponse update(Long id, BagdauletAskarStudentRequest request);

    void delete(Long id);

    Page<BagdauletAskarStudentResponse> getStudents(String search, String sortBy, String sortDir, int page, int size);
}
