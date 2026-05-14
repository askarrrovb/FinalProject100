package com.example.finalproject100.service;

import com.example.finalproject100.dto.request.BagdauletAskarCourseRequest;
import com.example.finalproject100.dto.response.BagdauletAskarCourseResponse;

import java.util.List;

public interface BagdauletAskarCourseService {

    List<BagdauletAskarCourseResponse> getAll();

    BagdauletAskarCourseResponse getById(Long id);

    BagdauletAskarCourseResponse create(BagdauletAskarCourseRequest request);

    BagdauletAskarCourseResponse update(Long id, BagdauletAskarCourseRequest request);

    void delete(Long id);

    List<BagdauletAskarCourseResponse> getByTeacherId(Long teacherId);
}
