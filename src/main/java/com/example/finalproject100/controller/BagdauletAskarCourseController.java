package com.example.finalproject100.controller;

import com.example.finalproject100.dto.request.BagdauletAskarCourseRequest;
import com.example.finalproject100.dto.response.BagdauletAskarCourseResponse;
import com.example.finalproject100.service.BagdauletAskarCourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class BagdauletAskarCourseController {

    private final BagdauletAskarCourseService courseService;

    @GetMapping
    public ResponseEntity<List<BagdauletAskarCourseResponse>> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BagdauletAskarCourseResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @GetMapping("/by-teacher")
    public ResponseEntity<List<BagdauletAskarCourseResponse>> getByTeacher(@RequestParam Long teacherId) {
        return ResponseEntity.ok(courseService.getByTeacherId(teacherId));
    }

    @PostMapping
    public ResponseEntity<BagdauletAskarCourseResponse> create(@Valid @RequestBody BagdauletAskarCourseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BagdauletAskarCourseResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody BagdauletAskarCourseRequest request) {
        return ResponseEntity.ok(courseService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
